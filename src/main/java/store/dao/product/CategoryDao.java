package store.dao.product;

import store.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    void save(Category category);

    List<Category> getAll();

    Optional<Category> getById(Long id);

    Optional<Category> getByName(String name);

    void update(Category category);

    void delete(Long id);
}
