package store.service.product;

import store.model.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);

    Product getById(Long id);

    List<Product> getAll();

    Product update(Product product);

    void delete(Long id);

    List<Product> getAvailableProducts(int productsPerPage, int pageNumber);

    int getNoOfPages(int productsPerPage);

    List<Product> getAvailableProductsByCategoryIds(List<Long> categoryIds);
}
