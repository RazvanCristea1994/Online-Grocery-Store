package store.facade.converter.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Category;

@Component
public class CategoryConverter implements Converter<Category, CategoryData> {

    @Autowired
    private Populator<Category, CategoryData> categoryPopulator;

    @Override
    public CategoryData convert(Category category) {

        CategoryData categoryData = new CategoryData();
        categoryPopulator.populate(categoryData, category);

        return categoryData;
    }
}
