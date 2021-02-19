package store.facade.converter.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.data.product.ProductViewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Product;

@Component
public class ProductViewReverseConverter implements Converter<ProductViewData, Product> {

    @Qualifier("productViewReversePopulator")
    @Autowired
    private Populator<ProductViewData, Product> productReversePopulator;

    @Override
    public Product convert(ProductViewData productViewData) {
        Product product = new Product();
        productReversePopulator.populate(product, productViewData);
        return product;
    }
}
