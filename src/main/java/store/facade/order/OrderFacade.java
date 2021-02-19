package store.facade.order;

import store.data.order.OrderData;
import store.model.ShoppingCart;

import java.util.List;

public interface OrderFacade {

    List<OrderData> getOrdersByUserEmail(String email);

    OrderData placeOrder(String email);
}
