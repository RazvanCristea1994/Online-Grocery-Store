package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.ShoppingCartItem;

@Component
public class ShoppingCartItemReverseConverter implements Converter<ShoppingCartItemData, ShoppingCartItem> {

    @Autowired
    private Populator<ShoppingCartItemData, ShoppingCartItem> orderItemReversePopulator;

    @Override
    public ShoppingCartItem convert(ShoppingCartItemData shoppingCartItemData) {

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        orderItemReversePopulator.populate(shoppingCartItem, shoppingCartItemData);
        return shoppingCartItem;
    }
}
