package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.OrderLineData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.ShoppingCartItem;

@Component
public class OrderLineConverter implements Converter<ShoppingCartItem, OrderLineData> {

    @Autowired
    private Populator<ShoppingCartItem, OrderLineData> populator;

    @Override
    public OrderLineData convert(ShoppingCartItem shoppingCartItem) {
        OrderLineData orderLineData = new OrderLineData();
        populator.populate(orderLineData, shoppingCartItem);
        return orderLineData;
    }
}
