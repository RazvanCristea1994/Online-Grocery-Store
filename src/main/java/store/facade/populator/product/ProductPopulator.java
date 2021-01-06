package store.facade.populator.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.data.product.ProductData;

import store.facade.populator.Populator;
import store.model.Category;
import store.model.Product;
import store.facade.converter.Converter;

@Component
public class ProductPopulator implements Populator<Product, ProductData> {

    @Autowired
    Converter<Category, CategoryData> converter;

    @Override
    public void populate(ProductData productData, Product product) {

        productData.setId(product.getId());
        productData.setName(product.getName());
        productData.setCategoryName(product.getCategory().getName());
        productData.setDescription(product.getDescription());
        productData.setPrice(product.getPrice());
        productData.setStock(product.getStock());
    }
}
