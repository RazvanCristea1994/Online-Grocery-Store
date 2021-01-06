package store.service.product;

import store.model.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> getAll();

    Category getCategoryByName(String name);

    Category getById(Long id);

    Category update (Category category);

    void delete(Long id);
}
