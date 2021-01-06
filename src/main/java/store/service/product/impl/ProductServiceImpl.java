package store.service.product.impl;

import org.springframework.stereotype.Service;
import store.service.product.ProductService;
import store.dao.product.ProductDao;
import store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product add(Product product) {

        Optional<Product> optionalProductName = productDao.getByName(product.getName());
        optionalProductName.ifPresentOrElse(
                foundCategory -> {
                    throw new IllegalArgumentException("This product name already exists. Choose another name or update the existing one");
                },
                () -> productDao.save(product)
        );

        return product;
    }

    @Override
    public Product update(Product product) {

        if (product != null) {
            Optional<Product> oldProductExists = productDao.getById(product.getId());
            Optional<Product> newProductName = productDao.getByName(product.getName());

            if (oldProductExists.isPresent()) {
                if (newProductName.isEmpty()) {
                    productDao.update(product);
                } else {
                    throw new IllegalArgumentException("Product name already used");
                }
            } else {
                throw new NoSuchElementException("The category to change was not found");
            }
        } else {
            throw new IllegalArgumentException("Illegal attempt! Please specify one of the products you want to update");
        }

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
    public void delete(Long ean) {

        productDao.getById(ean).ifPresentOrElse(foundProduct -> {
            productDao.delete(ean);
        }, () -> {
            throw new NoSuchElementException("The specified element does not exist");
        });
    }
}
