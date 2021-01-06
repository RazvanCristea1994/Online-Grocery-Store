package store.facade.category;

import store.data.product.CategoryData;
import store.model.Category;

import java.util.List;

public interface CategoryFacade {

    void save (CategoryData categoryData);

    Category update(CategoryData categoryData);

    List<CategoryData> getAll();

    CategoryData getById(Long id);

    void delete(Long id);
}
