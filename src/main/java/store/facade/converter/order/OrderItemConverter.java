package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.OrderItemData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.OrderItem;

@Component
public class OrderItemConverter implements Converter<OrderItem, OrderItemData> {

    @Autowired
    private Populator<OrderItem, OrderItemData> orderItemPopulator;

    @Override
    public OrderItemData convert(OrderItem orderItem) {

        OrderItemData orderItemData = new OrderItemData();
        orderItemPopulator.populate(orderItemData, orderItem);
        return orderItemData;
    }
}
