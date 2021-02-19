package store.facade.populator.order;

import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemRequestData;
import store.facade.populator.Populator;
import store.model.ShoppingCartItem;

@Component
public class ShoppingCartItemRequestReversePopulator implements Populator<ShoppingCartItem, ShoppingCartItemRequestData> {

    @Override
    public void populate(ShoppingCartItemRequestData shoppingCartItemRequestData, ShoppingCartItem shoppingCartItem) {

        shoppingCartItemRequestData.setId(shoppingCartItem.getProduct().getId());
        shoppingCartItemRequestData.setQuantity(shoppingCartItem.getQuantity());
    }
}
