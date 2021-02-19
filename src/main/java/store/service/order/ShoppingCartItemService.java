package store.service.order;

import store.model.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartItemService {

    ShoppingCartItem add(ShoppingCartItem shoppingCartItem);

    ShoppingCartItem add(ShoppingCartItem shoppingCartItem, List<ShoppingCartItem> shoppingCartItems);

    List<ShoppingCartItem> getAllByUserEmail(String email);

    ShoppingCartItem update(ShoppingCartItem shoppingCartItem);

    void deleteShoppingCartItemByProductIdAndUserEmail(Long id, String email);

    void deleteShoppingCartItemFromAnonymousCart(Long id, List<ShoppingCartItem> shoppingCartItems);
}
