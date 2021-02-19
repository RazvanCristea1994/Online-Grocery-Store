package store.facade.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.order.*;
import store.facade.converter.Converter;
import store.facade.order.ShoppingCartItemFacade;
import store.model.Product;
import store.model.ShoppingCartItem;
import store.service.order.ShoppingCartItemService;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ShoppingCartItemFacadeImpl implements ShoppingCartItemFacade {


    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private Converter<ShoppingCartItemData, ShoppingCartItem> shoppingCartItemReverseConverter;

    @Autowired
    private Converter<ShoppingCartItemRequestData, ShoppingCartItem> shoppingCartItemRequestConverter;

    @Autowired
    private Converter<ShoppingCartItem, ShoppingCartItemRequestData> shoppingCartItemRequestReverseConverter;

    @Autowired
    private Converter<ShoppingCartItem, OrderLineData> orderLineConverter;

    @Override
    public ShoppingCartItemResponseData addShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, String email) {

        ShoppingCartItemData shoppingCartItemData = new ShoppingCartItemData();
        shoppingCartItemData.setId(shoppingCartItemRequestData.getId());
        shoppingCartItemData.setEmail(email);
        shoppingCartItemData.setQuantity(shoppingCartItemRequestData.getQuantity());

        ShoppingCartItem shoppingCartItem = shoppingCartItemReverseConverter.convert(shoppingCartItemData);
        ShoppingCartItemResponseData shoppingCartItemResponseData = addShoppingCartItem(shoppingCartItem);

        CartCounter cartCounter = getCartCounterByEmail(email);
        shoppingCartItemResponseData.setNumberOfProducts(cartCounter.numberOfProducts);
        shoppingCartItemResponseData.setTotalPrice(cartCounter.totalPrice);
        return shoppingCartItemResponseData;
    }

    @Override
    public ShoppingCartItemDeleteResponseData deleteShoppingCartItemByProductIdAndUserEmail(Long id, String email) {

        shoppingCartItemService.deleteShoppingCartItemByProductIdAndUserEmail(id, email);

        ShoppingCartItemFacadeImpl.CartCounter cartCounter = getCartCounterByEmail(email);

        ShoppingCartItemDeleteResponseData shoppingCartItemDeleteResponseData = new ShoppingCartItemDeleteResponseData();
        shoppingCartItemDeleteResponseData.setNumberOfProducts(cartCounter.numberOfProducts);
        shoppingCartItemDeleteResponseData.setTotalPrice(cartCounter.totalPrice);
        return shoppingCartItemDeleteResponseData;
    }

    @Override
    public ShoppingCartItemDeleteResponseData deleteShoppingCartItemFromAnonymousCart(Long id, List<ShoppingCartItemRequestData> shoppingCart) {

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRequestConverter.convertAll(shoppingCart);
        shoppingCartItemService.deleteShoppingCartItemFromAnonymousCart(id, shoppingCartItems);

        ShoppingCartItemFacadeImpl.CartCounter cartCounter = getCartCounter(shoppingCartItems);

        shoppingCart.clear();
        shoppingCartItems.forEach(currentShoppingCartItem -> shoppingCart.add(shoppingCartItemRequestReverseConverter.convert(currentShoppingCartItem)));

        ShoppingCartItemDeleteResponseData shoppingCartItemDeleteResponseData = new ShoppingCartItemDeleteResponseData();
        shoppingCartItemDeleteResponseData.setNumberOfProducts(cartCounter.numberOfProducts);
        shoppingCartItemDeleteResponseData.setTotalPrice(cartCounter.totalPrice);
        return shoppingCartItemDeleteResponseData;
    }

    @Override
    public ShoppingCartItemResponseData addProductShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, List<ShoppingCartItemRequestData> shoppingCart) {

        ShoppingCartItem shoppingCartItem = shoppingCartItemRequestConverter.convert(shoppingCartItemRequestData);
        if (shoppingCartItem == null)
            throw new IllegalArgumentException("Invalid product ean!");
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRequestConverter.convertAll(shoppingCart);
        ShoppingCartItemResponseData shoppingCartItemResponseData = addShoppingCartItem(shoppingCartItem, shoppingCartItems);

        ShoppingCartItemFacadeImpl.CartCounter cartCounter = getCartCounter(shoppingCartItems);
        shoppingCartItemResponseData.setNumberOfProducts(cartCounter.numberOfProducts);
        shoppingCartItemResponseData.setTotalPrice(cartCounter.totalPrice);

        shoppingCart.clear();
        shoppingCartItems.forEach(currentShoppingCartItem -> shoppingCart.add(shoppingCartItemRequestReverseConverter.convert(currentShoppingCartItem)));

        return shoppingCartItemResponseData;
    }

    @Override
    public ShoppingCartItemResponseData updateShoppingCartItem(ShoppingCartItemRequestData shoppingCartItemRequestData, String email) {

        ShoppingCartItemData shoppingCartItemData = new ShoppingCartItemData();
        shoppingCartItemData.setId(shoppingCartItemRequestData.getId());
        shoppingCartItemData.setEmail(email);
        shoppingCartItemData.setQuantity(shoppingCartItemRequestData.getQuantity());

        ShoppingCartItem shoppingCartItem = shoppingCartItemReverseConverter.convert(shoppingCartItemData);

        ShoppingCartItemResponseData shoppingCartItemResponseData = new ShoppingCartItemResponseData();
        Product product = shoppingCartItem.getProduct();
        shoppingCartItemResponseData.setProductId(product.getId());
        shoppingCartItemResponseData.setProductName(product.getName());
        shoppingCartItemResponseData.setProductPrice(product.getPrice());

        try {
            int givenQuantity = shoppingCartItem.getQuantity();
            ShoppingCartItem updatedShoppingCartItem = shoppingCartItemService.update(shoppingCartItem);
            int updatedQuantity = updatedShoppingCartItem.getQuantity();
            shoppingCartItemResponseData.setQuantity(updatedQuantity);
            if (givenQuantity != updatedQuantity) {
                shoppingCartItemResponseData.setMessage(String.format("The given quantity exceeds the current stock! %d product(s) added to your cart!", updatedQuantity));
            } else {
                shoppingCartItemResponseData.setMessage("The product quantity successfully updated!");
            }
        } catch (IllegalArgumentException | NoSuchElementException exception) {
            shoppingCartItemResponseData.setMessage(exception.getMessage());
        }

        shoppingCartItemResponseData.setNumberOfProducts(getCartCounterByEmail(email).numberOfProducts);
        shoppingCartItemResponseData.setTotalPrice(getCartCounterByEmail(email).totalPrice);
        return shoppingCartItemResponseData;
    }

    private ShoppingCartItemResponseData addShoppingCartItem(ShoppingCartItem shoppingCartItem) {

        ShoppingCartItemResponseData shoppingCartItemResponseData = new ShoppingCartItemResponseData();
        Product product = shoppingCartItem.getProduct();
        shoppingCartItemResponseData.setProductId(product.getId());
        shoppingCartItemResponseData.setProductName(product.getName());
        shoppingCartItemResponseData.setProductPrice(product.getPrice());

        try {
            int givenQuantity = shoppingCartItem.getQuantity();
            shoppingCartItem = shoppingCartItemService.add(shoppingCartItem);
            int addedQuantity = shoppingCartItem.getQuantity();
            shoppingCartItemResponseData.setQuantity(addedQuantity);
            if (givenQuantity != addedQuantity) {
                shoppingCartItemResponseData.setMessage(String.format("The given quantity exceeds the current stock! %d product(s) added to your cart!", addedQuantity));
            } else {
                shoppingCartItemResponseData.setMessage("The product(s) successfully added to your cart!");
            }
        } catch (IllegalArgumentException exception) {
            shoppingCartItemResponseData.setMessage(exception.getMessage());
        }
        return shoppingCartItemResponseData;
    }

    private ShoppingCartItemResponseData addShoppingCartItem(ShoppingCartItem shoppingCartItem, List<ShoppingCartItem> shoppingCartItems) {

        ShoppingCartItemResponseData shoppingCartItemResponseData = getShoppingCartItemResponseData(shoppingCartItem);

        try {
            int givenQuantity = shoppingCartItem.getQuantity();
            shoppingCartItem = shoppingCartItemService.add(shoppingCartItem, shoppingCartItems);
            int addedQuantity = shoppingCartItem.getQuantity();
            shoppingCartItemResponseData.setQuantity(addedQuantity);
            if (givenQuantity != addedQuantity) {
                shoppingCartItemResponseData.setMessage(String.format("The given quantity exceeds the current stock! %d product(s) added to your cart!", addedQuantity));
            } else {
                shoppingCartItemResponseData.setMessage("The product(s) successfully added to your cart!");
            }
        } catch (IllegalArgumentException exception) {
            shoppingCartItemResponseData.setMessage(exception.getMessage());
        }

        return shoppingCartItemResponseData;
    }

    private ShoppingCartItemResponseData getShoppingCartItemResponseData(ShoppingCartItem shoppingCartItem) {

        ShoppingCartItemResponseData shoppingCartItemResponseData = new ShoppingCartItemResponseData();
        Product product = shoppingCartItem.getProduct();
        shoppingCartItemResponseData.setProductId(product.getId());
        shoppingCartItemResponseData.setProductName(product.getName());
        shoppingCartItemResponseData.setProductPrice(product.getPrice());
        return shoppingCartItemResponseData;
    }

    private CartCounter getCartCounterByEmail(String email) {

        List<ShoppingCartItem> productPrices = shoppingCartItemService.getAllByUserEmail(email);
        return productPrices
                .stream()
                .map(shoppingCartItem -> new CartCounter(shoppingCartItem.getQuantity(), shoppingCartItem.getProduct().getPrice()))
                .reduce(new CartCounter(), this::mergeTwoCartCountersIntoSingleCartCounter);
    }

    private ShoppingCartItemFacadeImpl.CartCounter getCartCounter(List<ShoppingCartItem> cart) {

        return cart
                .stream()
                .map(foundShoppingCartItem -> new ShoppingCartItemFacadeImpl.CartCounter(foundShoppingCartItem.getQuantity(), foundShoppingCartItem.getProduct().getPrice()))
                .reduce(new ShoppingCartItemFacadeImpl.CartCounter(), this::mergeTwoCartCountersIntoSingleCartCounter);
    }

    @Override
    public OrderLinesData getAllOrderLinesByEmail(String email) {

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByUserEmail(email);;
        return getOrderLinesData(shoppingCartItems);
    }

    @Override
    public OrderLinesData getAllOrderLinesForShoppingCart(List<ShoppingCartItemRequestData> shoppingCart) {

        if (shoppingCart == null) {
            return new OrderLinesData();
        }

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRequestConverter.convertAll(shoppingCart);
        return getOrderLinesData(shoppingCartItems);
    }

    private OrderLinesData getOrderLinesData(List<ShoppingCartItem> shoppingCartItems) {
        List<OrderLineData> orderLineDataList = orderLineConverter.convertAll(shoppingCartItems);

        OrderLinesData orderLinesData = new OrderLinesData();
        CartCounter cartCounter = getCartCounter(shoppingCartItems);

        orderLinesData.setOrderLines(orderLineDataList);
        orderLinesData.setTotalPrice(cartCounter.totalPrice);
        orderLinesData.setNumberOfProducts(cartCounter.numberOfProducts);

        return orderLinesData;
    }

    private CartCounter mergeTwoCartCountersIntoSingleCartCounter(CartCounter partialCartCounter, CartCounter nextCartCounter) {

        partialCartCounter.numberOfProducts = partialCartCounter.numberOfProducts + nextCartCounter.numberOfProducts;
        partialCartCounter.totalPrice = partialCartCounter.totalPrice + nextCartCounter.totalPrice;
        return partialCartCounter;
    }

    private static class CartCounter {

        private Integer numberOfProducts;
        private Double totalPrice;

        private CartCounter() {

            this(0, 0d);
        }

        private CartCounter(Integer numberOfProducts, Double productPrice) {

            this.numberOfProducts = numberOfProducts;
            this.totalPrice = numberOfProducts * productPrice;
        }
    }

}
