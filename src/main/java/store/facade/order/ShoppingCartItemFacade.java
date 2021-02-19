package store.facade.order;

import store.data.order.OrderLinesData;
import store.data.order.ShoppingCartItemDeleteResponseData;
import store.data.order.ShoppingCartItemRequestData;
import store.data.order.ShoppingCartItemResponseData;
import store.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemFacade {

    ShoppingCartItemResponseData addShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, String email);

    ShoppingCartItemDeleteResponseData deleteShoppingCartItemByProductIdAndUserEmail(Long id, String email);

    ShoppingCartItemDeleteResponseData deleteShoppingCartItemFromAnonymousCart(Long id, List<ShoppingCartItemRequestData> shoppingCart);

    ShoppingCartItemResponseData updateShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, String email);

    ShoppingCartItemResponseData addProductShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, List<ShoppingCartItemRequestData> shoppingCart);

    OrderLinesData getAllOrderLinesByEmail(String email);

    OrderLinesData getAllOrderLinesForShoppingCart(List<ShoppingCartItemRequestData> shoppingCart);
}
