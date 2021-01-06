package store.facade.populator.category;

import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.facade.populator.Populator;
import store.model.Category;

@Component
public class CategoryPopulator implements Populator<Category, CategoryData> {

    @Override
    public void populate(CategoryData categoryData, Category category) {
        categoryData.setId(category.getId());
        categoryData.setName(category.getName());
    }
}
