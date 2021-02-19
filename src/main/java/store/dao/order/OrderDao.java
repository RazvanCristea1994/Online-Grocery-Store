package store.dao.order;

import store.model.Order;

import java.util.List;

public interface OrderDao {

    void save(Order order);

    List<Order> getOrdersByUserEmail(String email);

    List<Order> getAll();

    void delete(Long id);
}
