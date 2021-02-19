package store.facade.populator.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.OrderData;
import store.data.order.OrderItemData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Order;
import store.model.OrderItem;

import java.util.List;

@Component
public class OrderPopulator implements Populator<Order, OrderData> {

    @Autowired
    private Converter<OrderItem, OrderItemData> orderItemConverter;

    @Override
    public void populate(OrderData orderData, Order order) {
        orderData.setId(order.getId());
        orderData.setOrderDate(order.getOrderDate());
        orderData.setTotalCost(order.getTotalCost());
        orderData.setEmail(order.getUser().getEmail());
        List<OrderItemData> orderItems = orderItemConverter.convertAll(order.getOrderItems());
        orderData.setOrderItems(orderItems);
    }
}
