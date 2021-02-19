package store.dao.order.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.order.OrderItemDao;
import store.model.OrderItem;

import java.util.List;

@Repository
public class OrderItemDaoImpl extends HibernateAbstractCrudRepository<Long, OrderItem> implements OrderItemDao {

    @Override
    protected Class<OrderItem> getValueClass() {
        return OrderItem.class;
    }

    @Override
    public List<OrderItem> getAllByUserEmail(String email) {

        Query<OrderItem> query = super.getCurrentSession().createQuery("select orderItem from " + getValueClass().getName() +
                " orderItem where orderItem.id.user.email = :email", OrderItem.class);
        query.setParameter("email", email);
        return query.list();
    }
}
