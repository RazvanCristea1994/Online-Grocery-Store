package store.facade.product;

import store.data.product.ProductData;
import store.data.product.ProductDetailsData;
import store.model.Product;

import java.util.List;

public interface ProductFacade {

    Product save(ProductData productData);

    Product update(ProductData productData);

    ProductData getById(Long id);

    List<ProductData> getAll();

    void delete(Long id);

    List<ProductData> getAvailableProductDatas(int productsPerPage, int pageNumber);

    int getNoOfPages(int productsPerPage);

    List<ProductData> getAvailableProductDatasByCategoryIds(List<Long> categoryIds);

    ProductDetailsData getProductDetailsDataById(Long id);
}
