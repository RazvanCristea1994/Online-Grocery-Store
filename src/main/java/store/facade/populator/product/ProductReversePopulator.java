package store.facade.populator.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.data.product.ProductData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Category;
import store.model.Product;
import store.service.product.CategoryService;

@Component
public class ProductReversePopulator implements Populator<ProductData, Product> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void populate(Product product, ProductData productData) {

        product.setId(productData.getId());
        product.setName(productData.getName());
        product.setCategory(categoryService.getCategoryByName(productData.getCategoryName()));
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setStock(productData.getStock());
    }
}
