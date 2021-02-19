package store.dao.order;

import store.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemDao {

    void save(ShoppingCartItem shoppingCartItem);

    Optional<ShoppingCartItem> getById(Long id);

    Optional<ShoppingCartItem> getByProductIdAndUserEmail(Long id, String email);

    List<ShoppingCartItem> getByUserEmail(String email);

    void update(ShoppingCartItem shoppingCartItem);

    void deleteByProductIdAndUserEmail(Long id, String email);
}
