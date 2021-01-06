package store.facade.populator.category;

import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.facade.populator.Populator;
import store.model.Category;

@Component
public class CategoryReversePopulator implements Populator<CategoryData, Category> {


    @Override
    public void populate(Category category, CategoryData categoryData) {

        category.setId(categoryData.getId());
        category.setName(categoryData.getName());
    }
}
