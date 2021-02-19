package store.service.order;

import store.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByUserEmail(String email);

    Order placeOrder(String email);

    void refactorShippingCart(String email);

    List<Order> getAll();
}
