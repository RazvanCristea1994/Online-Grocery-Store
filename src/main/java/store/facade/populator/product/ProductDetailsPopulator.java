package store.facade.populator.product;

import org.springframework.stereotype.Component;
import store.data.product.ProductDetailsData;
import store.facade.populator.Populator;
import store.model.Product;

@Component
public class ProductDetailsPopulator implements Populator<Product, ProductDetailsData> {

    @Override
    public void populate(ProductDetailsData productDetailsData, Product product) {

        productDetailsData.setId(product.getId());
        productDetailsData.setDescription(product.getDescription());
        productDetailsData.setName(product.getName());
        productDetailsData.setPrice(product.getPrice());
    }
}
