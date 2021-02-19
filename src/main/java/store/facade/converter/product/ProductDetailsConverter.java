package store.facade.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.ProductDetailsData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Product;

@Component
public class ProductDetailsConverter implements Converter<Product, ProductDetailsData> {

    @Autowired
    private Populator<Product, ProductDetailsData> populator;

    @Override
    public ProductDetailsData convert(Product product) {

        ProductDetailsData productDetailsData = new ProductDetailsData();
        populator.populate(productDetailsData, product);
        return productDetailsData;
    }
}
