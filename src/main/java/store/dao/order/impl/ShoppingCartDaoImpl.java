package store.dao.order.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.order.ShoppingCartDao;
import store.model.ShoppingCart;

@Repository
public class ShoppingCartDaoImpl extends HibernateAbstractCrudRepository<Long, ShoppingCart> implements ShoppingCartDao {

    @Override
    public ShoppingCart getShoppingCartByUserEmail(String searchedEmail) {

        String queryString = "FROM " + getValueClass().getName() + " shoppingCart WHERE shoppingCart.email =: userEmail";
        Query<ShoppingCart> query = super.getCurrentSession().createQuery(queryString, getValueClass());
        query.setParameter("userEmail", searchedEmail);
        return query.uniqueResult();
    }

    @Override
    protected Class<ShoppingCart> getValueClass() {
        return ShoppingCart.class;
    }
}
