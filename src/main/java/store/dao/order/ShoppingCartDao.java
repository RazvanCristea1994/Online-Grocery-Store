package store.dao.order;

import store.model.ShoppingCart;

public interface ShoppingCartDao {

    void save(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCartByUserEmail(String email);
}
