package store.facade.populator.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.data.product.ProductViewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Category;
import store.model.Product;
import store.service.product.CategoryService;

@Component
public class ProductViewReversePopulator implements Populator<ProductViewData, Product> {

    @Autowired
    private Converter<CategoryData, Category> converter;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void populate(Product product, ProductViewData productViewData) {
        product.setCategory(categoryService.getById(productViewData.getCategoryId()));
        product.setDescription(productViewData.getDescription());
        product.setName(productViewData.getName());
        product.setPrice(productViewData.getPrice());
        product.setId(productViewData.getId());
        product.setStock(productViewData.getStock());
    }

}
