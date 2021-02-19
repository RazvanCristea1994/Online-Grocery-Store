package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.OrderData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Order;

@Component
public class OrderConverter implements Converter<Order, OrderData> {

    @Autowired
    private Populator<Order, OrderData> populator;

    @Override
    public OrderData convert(Order order) {
        OrderData orderData = new OrderData();
        populator.populate(orderData, order);
        return orderData;
    }
}
