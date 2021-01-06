package store.facade.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.ProductData;

import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Product;

@Component
public class ProductConverter implements Converter<Product, ProductData> {

    @Autowired
    private Populator<Product, ProductData> populator;

    @Override
    public ProductData convert(Product product) {

        ProductData productData = new ProductData();
        populator.populate(productData, product);

        return productData;
    }
}
