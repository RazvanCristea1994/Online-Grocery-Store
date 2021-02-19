package store.dao.product.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.product.ProductDao;
import store.model.Product;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl extends HibernateAbstractCrudRepository<Long, Product> implements ProductDao {

    @Override
    public Optional<Product> getByName(String searchedName) {

        String queryString = "FROM " + getValueClass().getName() + " WHERE name =: productName";
        Query<Product> query = super.getCurrentSession().createQuery(queryString, Product.class);
        query.setParameter("productName", searchedName);

        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    public List<Product> getAvailableProducts(int productsPerPage, int pageNumber) {
        Session session = super.getCurrentSession();
        Class<Product> valueClass = Product.class;

        int offset = (pageNumber - 1) * productsPerPage;

        List<Product> products = session.createQuery("from " + valueClass.getName() + " where stock > 0 ", valueClass)
                .setFirstResult(offset)
                .setMaxResults(productsPerPage).list();

        return products;
    }

    @Override
    public int getNoOfPages(int productsPerPage) {
        Session session = super.getCurrentSession();
        Class<Product> valueClass = Product.class;

        int productsCount = ((Long)session.createQuery("select count(*) " + "from " + valueClass.getName() + " where stock > 0 ").uniqueResult()).intValue();

        return (productsCount + productsPerPage -1) / productsPerPage;
    }

    @Override
    public List<Product> getAvailableProductsByCategoryIds(List<Long> categoryIds) {
        Query<Product> query = super.getCurrentSession().createQuery("select distinct p from Product p join p.categories c where c.id in (:ids) and p.stock > 0", Product.class);
        query.setParameter("ids", categoryIds);
        return query.list();
    }

    @Override
    protected Class<Product> getValueClass() {
        return Product.class;
    }
}
