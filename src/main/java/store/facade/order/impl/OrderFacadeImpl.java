package store.facade.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.OrderData;
import store.facade.converter.Converter;
import store.facade.order.OrderFacade;
import store.model.Order;
import store.service.order.OrderService;

import java.util.List;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Converter<Order, OrderData> orderConverter;

    @Override
    public List<OrderData> getOrdersByUserEmail(String email) {
        List<Order> ordersByUserEmail = orderService.getOrdersByUserEmail(email);
        return orderConverter.convertAll(ordersByUserEmail);
    }

    @Override
    public OrderData placeOrder(String email) {

        Order placedOrder = new Order();
        try {
            placedOrder = orderService.placeOrder(email);
        } catch (IllegalArgumentException e) {
            orderService.refactorShippingCart(email);
            throw e;
        }
        return orderConverter.convert(placedOrder);
    }
}
