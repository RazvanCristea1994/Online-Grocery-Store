package store.dao.order.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.order.OrderDao;
import store.model.Order;

import java.util.List;

@Repository
public class OrderDaoImpl extends HibernateAbstractCrudRepository<Long, Order> implements OrderDao {

    @Override
    protected Class<Order> getValueClass() {
        return Order.class;
    }

    @Override
    public List<Order> getOrdersByUserEmail(String email) {

        Query<Order> query = super.getCurrentSession().createQuery("select order from "
                + getValueClass().getName() + " order where order.user.email = :email", Order.class);
        query.setParameter("email", email);
        return query.list();
    }
}
