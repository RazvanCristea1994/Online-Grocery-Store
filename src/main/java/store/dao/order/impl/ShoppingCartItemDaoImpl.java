package store.dao.order.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.order.ShoppingCartItemDao;
import store.model.ShoppingCart;
import store.model.ShoppingCartItem;


import java.util.List;
import java.util.Optional;

@Repository
public class ShoppingCartItemDaoImpl extends HibernateAbstractCrudRepository<Long, ShoppingCartItem> implements ShoppingCartItemDao {

    @Override
    public Optional<ShoppingCartItem> getByProductIdAndUserEmail(Long searchedId, String searchedEmail) {

        String queryString = "FROM " + getValueClass().getName() + " shoppingCartItem WHERE shoppingCartItem.product.id =: productId " +
                "AND shoppingCartItem.shoppingCart.user.email =: userEmail";
        Query<ShoppingCartItem> query = super.getCurrentSession().createQuery(queryString, getValueClass());
        query.setParameter("productId", searchedId);
        query.setParameter("userEmail", searchedEmail);

        return query.uniqueResultOptional();
    }

    @Override
    public List<ShoppingCartItem> getByUserEmail(String searchedEmail) {

        String queryString = "FROM " + getValueClass().getName() + " shoppingCartItem WHERE shoppingCartItem.shoppingCart.user.email =: userEmail";
        Query<ShoppingCartItem> query = super.getCurrentSession().createQuery(queryString, getValueClass());
        query.setParameter("userEmail", searchedEmail);

        return null;
    }

    @Override
    public void deleteByProductIdAndUserEmail(Long searchedId, String searchedEmail) {

        Optional<ShoppingCartItem> shoppingCartItem = getByProductIdAndUserEmail(searchedId,searchedEmail);
        shoppingCartItem.ifPresent(o -> super.delete(shoppingCartItem.get().getId()));
    }

    @Override
    protected Class<ShoppingCartItem> getValueClass() {
        return ShoppingCartItem.class;
    }
}
