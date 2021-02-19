package store.facade.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.data.product.ProductData;
import store.data.product.ProductDetailsData;
import store.data.product.ProductViewData;
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

    @Qualifier("productViewReverseConverter")
    @Autowired
    private Converter<ProductViewData, Product> productViewDataConverter;

    @Autowired
    private Converter<Product, ProductDetailsData> productDetailsConverter;

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
    public void delete(Long id) {

        productService.delete(id);
    }

    @Override
    public List<ProductData> getAvailableProductDatas(int productsPerPage, int pageNumber) {

        List<Product> products = productService.getAvailableProducts(productsPerPage, pageNumber);
        return productConverter.convertAll(products);
    }

    @Override
    public int getNoOfPages(int productsPerPage) {

        return productService.getNoOfPages(productsPerPage);
    }

    @Override
    public List<ProductData> getAvailableProductDatasByCategoryIds(List<Long> categoryIds) {

        List<Product> filteredProducts = productService.getAvailableProductsByCategoryIds(categoryIds);
        return productConverter.convertAll(filteredProducts);
    }

    @Override
    public ProductDetailsData getProductDetailsDataById(Long id) {
        return productDetailsConverter.convert(productService.getById(id));
    }
}
