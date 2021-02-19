package store.service.order;

import store.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getOrCreateShoppingCartByUserEmail(String email);
}
