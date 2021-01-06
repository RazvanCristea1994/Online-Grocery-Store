package store.dao.product;

import store.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void save(Product product);

    List<Product> getAll();

    Optional<Product> getById(Long id);

    Optional<Product> getByName(String name);

    void update(Product product);

    void delete(Long ean);

    //ToDo:
//    List<Product> getAvailableProducts(int productsPerPage, int pageNumber);
//
//    int getNoOfPages(int productsPerPage);
//
//    List<Product> getAvailableProductsByCategoryIds(List<Integer> categoryIds);
}
