package store.facade.populator.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemData;
import store.facade.populator.Populator;
import store.model.Product;
import store.model.ShoppingCart;
import store.model.ShoppingCartItem;
import store.service.order.ShoppingCartService;
import store.service.product.ProductService;

@Component
public class ShoppingCartItemReversePopulator implements Populator<ShoppingCartItemData, ShoppingCartItem> {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public void populate(ShoppingCartItem shoppingCartItem, ShoppingCartItemData shoppingCartItemData) {

        Product product = productService.getById(shoppingCartItemData.getId());
        ShoppingCart shoppingCart = shoppingCartService.getOrCreateShoppingCartByUserEmail(shoppingCartItemData.getEmail());
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(shoppingCartItemData.getQuantity());
    }
}
