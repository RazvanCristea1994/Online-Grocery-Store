package store.service.product.impl;

import org.springframework.stereotype.Service;
import store.service.product.CategoryService;
import store.dao.product.CategoryDao;
import store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void add(Category category) {

        Optional<Category> optionalCategoryName = categoryDao.getByName(category.getName());
        optionalCategoryName.ifPresent(foundCategory -> {
            throw new IllegalArgumentException("This category name already exists. Choose another name or update the existing one");
        });

        categoryDao.save(category);
    }

    @Override
    public List<Category> getAll() {
        List list = categoryDao.getAll();
        return list;
    }

    @Override
    public Category getCategoryByName(String name) {

        Optional<Category> category = categoryDao.getByName(name);
        return category.orElse(null);
    }

    @Override
    public Category getById(Long id) {

        Optional<Category> category = categoryDao.getById(id);
        return category.orElse(null);
    }

    @Override
    public Category update(Category category) {

        if (category != null) {
            Optional<Category> oldCategoryExists = categoryDao.getById(category.getId());
            Optional<Category> newCategoryName = categoryDao.getByName(category.getName());

            if (oldCategoryExists.isPresent()) {
                if (newCategoryName.isEmpty()) {
                    categoryDao.update(category);
                } else {
                    throw new IllegalArgumentException("Category name already used");
                }
            } else {
                throw new NoSuchElementException("The category to change was not found");
            }
        } else {
            throw new IllegalArgumentException("Illegal attempt! Please specify one of the categories you want to update");
        }

        return category;
    }

    @Override
    public void delete(Long id) {

        Optional<Category> category = categoryDao.getById(id);
        if(category.isPresent()) {
            if (category.get().getProductList().isEmpty()){
                categoryDao.delete(id);
            } else {
                throw new IllegalArgumentException("The category has products. Please remove products from category and try again");
            }
        } else {
            throw new NoSuchElementException("Category not found");
        }
    }
}
