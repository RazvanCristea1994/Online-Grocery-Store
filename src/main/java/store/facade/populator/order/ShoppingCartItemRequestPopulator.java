package store.facade.populator.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemRequestData;
import store.facade.populator.Populator;
import store.model.Product;
import store.model.ShoppingCartItem;
import store.service.product.ProductService;

@Component
public class ShoppingCartItemRequestPopulator implements Populator<ShoppingCartItemRequestData, ShoppingCartItem> {

    @Autowired
    private ProductService productService;

    @Override
    public void populate(ShoppingCartItem shoppingCartItem, ShoppingCartItemRequestData shoppingCartItemRequestData) {

        Product product = productService.getById(shoppingCartItemRequestData.getId());
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(shoppingCartItemRequestData.getQuantity());
    }
}
