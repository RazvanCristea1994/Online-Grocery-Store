package store.facade.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.ProductData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Product;

@Component
public class ProductReverseConverter implements Converter<ProductData, Product> {

    @Autowired
    private Populator<ProductData, Product> productReversePopulator;

    @Override
    public Product convert(ProductData productData){

        Product product = new Product();
        productReversePopulator.populate(product, productData);

        return product;
    }
}
