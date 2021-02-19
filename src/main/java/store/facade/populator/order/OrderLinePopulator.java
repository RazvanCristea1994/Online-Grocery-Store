package store.facade.populator.order;

import org.springframework.stereotype.Component;
import store.data.order.OrderLineData;
import store.facade.populator.Populator;
import store.model.Product;
import store.model.ShoppingCartItem;

@Component
public class OrderLinePopulator implements Populator<ShoppingCartItem, OrderLineData> {

    @Override
    public void populate(OrderLineData orderLineData, ShoppingCartItem shoppingCartItem) {

        Product product = shoppingCartItem.getProduct();
        Double totalPrice = product.getPrice() * shoppingCartItem.getQuantity();

        orderLineData.setProductId(product.getId());
        orderLineData.setProductName(product.getName());
        orderLineData.setProductPrice(product.getPrice());
        orderLineData.setQuantity(shoppingCartItem.getQuantity());
        orderLineData.setTotalPrice(totalPrice);
    }
}
