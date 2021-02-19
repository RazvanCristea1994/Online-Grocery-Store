package store.facade.converter.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.ProductViewData;
import store.facade.converter.Converter;
import store.facade.populator.product.ProductViewPopulator;
import store.model.Product;

@Component
public class ProductViewConverter implements Converter<ProductViewData, Product> {

    @Autowired
    private ProductViewPopulator productViewPopulator;

    @Override
    public Product convert(ProductViewData productViewData) {
        Product product = new Product();
        productViewPopulator.populate(product, productViewData);
        return product;
    }
}
