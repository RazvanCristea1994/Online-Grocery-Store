package store.facade.populator.order;

import org.springframework.stereotype.Component;
import store.data.order.OrderItemData;
import store.facade.populator.Populator;
import store.model.OrderItem;

@Component
public class OrderItemPopulator implements Populator<OrderItem, OrderItemData> {

    @Override
    public void populate(OrderItemData orderItemData, OrderItem orderItem) {
        orderItemData.setOrderId(orderItem.getOrder().getId());
        orderItemData.setProductId(orderItem.getProduct().getId());
        orderItemData.setProductName(orderItem.getProduct().getName());
        orderItemData.setQuantity(orderItem.getQuantity());
        orderItemData.setPrice(orderItem.getPrice());
    }
}
