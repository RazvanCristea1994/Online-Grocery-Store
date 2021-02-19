package store.service.product.impl;

import org.springframework.stereotype.Service;
import store.dao.product.CategoryDao;
import store.service.product.ProductService;
import store.dao.product.ProductDao;
import store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Product add(Product product) {

        Optional<Product> optionalProductName = productDao.getByName(product.getName());
        if(optionalProductName.isPresent()){
            throw new IllegalArgumentException("This product name already exists. Choose another name or update the existing one.");
        }

        if (product.getCategory() == null){
            throw new IllegalArgumentException("This category does not exist. Choose an existing one.");
        }

        productDao.save(product);
        return product;
    }

    @Override
    public Product update(Product product) {

        if (product == null){
            throw new IllegalArgumentException("Illegal attempt! Please specify one of the products you want to update");
        }

        Optional<Product> oldProduct = productDao.getById(product.getId());
        if (oldProduct.isEmpty()) {
            throw new NoSuchElementException("The product to change was not found");
        }

        if (!oldProduct.get().getName().equals(product.getName())) {
            Optional<Product> newProductName = productDao.getByName(product.getName());
            if (newProductName.isPresent()) {
                throw new IllegalArgumentException("Product name already used");
            }
        }

        if (product.getCategory() == null){
            throw new IllegalArgumentException("This category does not exist");
        }

        productDao.update(product);
        return product;
    }

    @Override
    public Product getById(Long id) {

        Optional<Product> optionalProduct = productDao.getById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("There is no product with given ID");
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAll() {

        return productDao.getAll();
    }

    @Override
    public void delete(Long id) {

        productDao.getById(id).ifPresentOrElse(foundProduct -> {
            productDao.delete(id);
        }, () -> {
            throw new NoSuchElementException("The specified element does not exist");
        });
    }

    @Override
    public List<Product> getAvailableProducts(int productsPerPage, int pageNumber) {

        List<Product> products = productDao.getAvailableProducts(productsPerPage, pageNumber);
        return products;
    }

    @Override
    public int getNoOfPages(int productsPerPage) {
        return productDao.getNoOfPages(productsPerPage);
    }

    @Override
    public List<Product> getAvailableProductsByCategoryIds(List<Long> categoryIds) {

        List<Long> validCategoryIds = new ArrayList<>();

        for (Long categoryId : categoryIds) {
            if (categoryDao.getById(categoryId).isPresent()) {
                validCategoryIds.add(categoryId);
            }
        }

        return productDao.getAvailableProductsByCategoryIds(validCategoryIds);
    }
}
