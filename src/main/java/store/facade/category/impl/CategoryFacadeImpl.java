package store.facade.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.product.CategoryData;
import store.facade.category.CategoryFacade;
import store.facade.converter.Converter;
import store.model.Category;
import store.service.product.CategoryService;

import java.util.List;

@Component
public class CategoryFacadeImpl implements CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Converter<CategoryData, Category> categoryReverseConverter;

    @Autowired
    private Converter<Category, CategoryData> categoryConverter;

    @Override
    public void save(CategoryData categoryData) {

        Category category = categoryReverseConverter.convert(categoryData);
        categoryService.add(category);
    }

    @Override
    public Category update(CategoryData categoryData) {

        Category category = categoryReverseConverter.convert(categoryData);
        return categoryService.update(category);
    }

    @Override
    public List<CategoryData> getAll() {

        return categoryConverter.convertAll(categoryService.getAll());
    }

    @Override
    public CategoryData getById(Long id) {

        return categoryConverter.convert(categoryService.getById(id));
    }

    @Override
    public void delete(Long id) {

        categoryService.delete(id);
    }
}
