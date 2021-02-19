package store.dao.product.impl;

import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.product.CategoryDao;
import store.model.Category;
import org.hibernate.query.Query;

import java.util.Optional;

@Repository
public class CategoryDaoImpl extends HibernateAbstractCrudRepository<Long, Category> implements CategoryDao {

    @Override
    public Optional<Category> getByName(String searchedName) {

        String queryString = "FROM " + getValueClass().getName() + " WHERE name =: categoryName";
        Query<Category> query = super.getCurrentSession().createQuery(queryString, Category.class);
        query.setParameter("categoryName", searchedName);

        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    protected Class<Category> getValueClass() {
        return Category.class;
    }
}
