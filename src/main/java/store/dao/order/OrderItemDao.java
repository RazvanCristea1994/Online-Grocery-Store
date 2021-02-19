package store.dao.order;

import store.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemDao {

    void save(OrderItem OrderItem);

    Optional<OrderItem> getById(Long id);

    List<OrderItem> getAllByUserEmail(String email);
}
