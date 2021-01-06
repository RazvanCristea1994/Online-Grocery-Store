package store.facade.converter.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Category;

@Component
public class CategoryReverseConverter implements Converter<CategoryData, Category> {

    @Autowired
    Populator<CategoryData, Category> categoryPopulator;

    @Override
    public Category convert(CategoryData categoryData) {

        Category category = new Category();
        categoryPopulator.populate(category, categoryData);

        return category;
    }
}
