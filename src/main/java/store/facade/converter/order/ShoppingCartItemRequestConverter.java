package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemRequestData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.ShoppingCartItem;

@Component
public class ShoppingCartItemRequestConverter implements Converter<ShoppingCartItemRequestData, ShoppingCartItem> {

    @Autowired
    private Populator<ShoppingCartItemRequestData, ShoppingCartItem> shoppingCartItemRequestPopulator;

    @Override
    public ShoppingCartItem convert(ShoppingCartItemRequestData shoppingCartItemRequestData) {

        try {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItemRequestPopulator.populate(shoppingCartItem, shoppingCartItemRequestData);
            return shoppingCartItem;
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
