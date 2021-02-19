package store.facade.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemRequestData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.ShoppingCartItem;

@Component
public class ShoppingCartItemRequestReverseConverter implements Converter<ShoppingCartItem, ShoppingCartItemRequestData> {

    @Autowired
    private Populator<ShoppingCartItem, ShoppingCartItemRequestData> shoppingCartItemRequestReversePopulator;

    @Override
    public ShoppingCartItemRequestData convert(ShoppingCartItem shoppingCartItem) {

        ShoppingCartItemRequestData shoppingCartItemRequestData = new ShoppingCartItemRequestData();
        shoppingCartItemRequestReversePopulator.populate(shoppingCartItemRequestData, shoppingCartItem);
        return shoppingCartItemRequestData;
    }
}
