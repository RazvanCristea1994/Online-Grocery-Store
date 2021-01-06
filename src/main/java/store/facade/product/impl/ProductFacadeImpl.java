package store.facade.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.ProductData;
import store.facade.converter.Converter;
import store.facade.product.ProductFacade;
import store.model.Product;
import store.service.product.ProductService;

import java.util.List;

@Component
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    ProductService productService;

    @Autowired
    Converter<Product, ProductData> productConverter;

    @Autowired
    Converter<ProductData, Product> productReverseConverter;

    @Override
    public Product save(ProductData productData) {

        Product product = productReverseConverter.convert(productData);
        return productService.add(product);
    }

    @Override
    public Product update(ProductData productData) {

        Product product = productReverseConverter.convert(productData);
        return productService.update(product);
    }

    @Override
    public ProductData getById(Long id) {

        return productConverter.convert(productService.getById(id));
    }

    @Override
    public List<ProductData> getAll() {

        List<Product> products = productService.getAll();
        return productConverter.convertAll(products);
    }

    @Override
    public void delete(Long ean) {

        productService.delete(ean);
    }
}
