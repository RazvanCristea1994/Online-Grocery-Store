package store.dao.product.impl;

import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.product.ProductDao;
import store.model.Product;
import org.hibernate.query.Query;

import java.util.Optional;

@Repository
public class ProductDaoImpl extends HibernateAbstractCrudRepository<Long, Product> implements ProductDao {

    @Override
    public Optional<Product> getByName(String searchedName) {

        Query<Product> query = super.getCurrentSession().createQuery("FROM " + getValueClass().getName() +
                " WHERE name =: productName", Product.class);
        query.setParameter("productName", searchedName);

        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    protected Class<Product> getValueClass() {
        return Product.class;
    }
}
