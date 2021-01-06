package store.facade.product;

import store.data.product.ProductData;
import store.model.Product;

import java.util.List;

public interface ProductFacade {

    Product save(ProductData productData);

    Product update(ProductData productData);

    ProductData getById(Long id);

    List<ProductData> getAll();

    void delete(Long ean);

    //List<ProductData> getAvailableProductDatas();
}
