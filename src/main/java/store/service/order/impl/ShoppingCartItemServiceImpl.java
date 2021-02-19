package store.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dao.order.ShoppingCartItemDao;
import store.model.Product;
import store.model.ShoppingCartItem;
import store.service.order.ShoppingCartItemService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemDao shoppingCartItemDao;

    @Override
    public ShoppingCartItem add(ShoppingCartItem shoppingCartItem) {

        if (shoppingCartItem.getProduct() == null) {
            throw new IllegalArgumentException("Invalid product!");
        }

        Product product = shoppingCartItem.getProduct();
        if (product.getStock().equals(0)){
            throw new IllegalArgumentException("The given product is no longer in stock!");
        }

        Optional<ShoppingCartItem> optionalShoppingCartItem = shoppingCartItemDao.getByProductIdAndUserEmail(product.getId(), shoppingCartItem.getShoppingCart().getUser().getEmail());
        optionalShoppingCartItem.ifPresentOrElse(
                foundShoppingCartItem -> mergeQuantityForExistingShoppingCartItem(shoppingCartItem, foundShoppingCartItem),
                () -> saveShoppingCartItem(shoppingCartItem));
        return shoppingCartItem;
    }

    @Override
    public ShoppingCartItem add(ShoppingCartItem shoppingCartItem, List<ShoppingCartItem> shoppingCartItems) {

        checkIfShoppingCartItemIsValid(shoppingCartItem);
        Product product = shoppingCartItem.getProduct();
        Optional<ShoppingCartItem> optionalShoppingCartItem = shoppingCartItems.stream()
                .filter(currentShoppingCartItem -> currentShoppingCartItem.getProduct().getId().equals(product.getId()))
                .findFirst();
        optionalShoppingCartItem.ifPresentOrElse(
                foundShoppingCartItem -> {
                    mergeQuantityForNonPersistentShoppingCartItem(shoppingCartItem, foundShoppingCartItem);
                }, () -> saveNonPersistentShoppingCartItem(shoppingCartItem, shoppingCartItems)
        );
        return shoppingCartItem;
    }

    @Override
    public ShoppingCartItem update(ShoppingCartItem shoppingCartItem) {

        Product product = shoppingCartItem.getProduct();
        if(product.getStock().equals(0)) {
            throw new IllegalArgumentException("The given product is no longer in stock!");
        }

        Optional<ShoppingCartItem> optionalShoppingCartItem = shoppingCartItemDao.getByProductIdAndUserEmail(product.getId(), shoppingCartItem.getShoppingCart().getUser().getEmail());
        if(optionalShoppingCartItem.isPresent()) {
            if(shoppingCartItem.getQuantity() > product.getStock()) {
                shoppingCartItem.setQuantity(product.getStock());
            }
            shoppingCartItem.setId(optionalShoppingCartItem.get().getId());
            shoppingCartItemDao.update(shoppingCartItem);
        } else {
            throw new NoSuchElementException("Specified order item does not exist.");
        }

        return shoppingCartItem;
    }

    @Override
    public List<ShoppingCartItem> getAllByUserEmail(String email) {

        return shoppingCartItemDao.getByUserEmail(email);
    }

    @Override
    public void deleteShoppingCartItemByProductIdAndUserEmail(Long ean, String email) {
        shoppingCartItemDao.deleteByProductIdAndUserEmail(ean, email);
    }

    @Override
    public void deleteShoppingCartItemFromAnonymousCart(Long ean, List<ShoppingCartItem> shoppingCartItems){
        shoppingCartItems.removeIf(shoppingCartItem -> shoppingCartItem.getProduct().getId().equals(ean));
    }

    private void mergeQuantityForNonPersistentShoppingCartItem(ShoppingCartItem shoppingCartItem, ShoppingCartItem foundShoppingCartItem) {

        Product product = foundShoppingCartItem.getProduct();
        int totalQuantity = foundShoppingCartItem.getQuantity() + shoppingCartItem.getQuantity();
        if (totalQuantity > product.getStock()) {
            totalQuantity = product.getStock();
        }
        shoppingCartItem.setQuantity(totalQuantity - foundShoppingCartItem.getQuantity());
        foundShoppingCartItem.setQuantity(totalQuantity);
    }

    private void saveNonPersistentShoppingCartItem(ShoppingCartItem shoppingCartItem, List<ShoppingCartItem> shoppingCartItems) {

        Product product = shoppingCartItem.getProduct();
        if (shoppingCartItem.getQuantity() > product.getStock()) {
            shoppingCartItem.setQuantity(product.getStock());
        }
        shoppingCartItems.add(shoppingCartItem);
    }


    private void checkIfShoppingCartItemIsValid(ShoppingCartItem shoppingCartItem) {

        if (shoppingCartItem.getProduct() == null) {
            throw new IllegalArgumentException("Invalid product!");
        }

        Product product = shoppingCartItem.getProduct();
        if (product.getStock().equals(0)) {
            throw new IllegalArgumentException("The given product is no longer in stock!");
        }
    }

    private void saveShoppingCartItem(ShoppingCartItem shoppingCartItem) {

        Product product = shoppingCartItem.getProduct();
        if (shoppingCartItem.getQuantity() > product.getStock()){
            shoppingCartItem.setQuantity(product.getStock());
        }
        shoppingCartItemDao.save(shoppingCartItem);
    }

    private void mergeQuantityForExistingShoppingCartItem(ShoppingCartItem shoppingCartItem, ShoppingCartItem foundShoppingCartItem) {

        mergeQuantityForNonPersistentShoppingCartItem(shoppingCartItem, foundShoppingCartItem);
        shoppingCartItemDao.update(foundShoppingCartItem);
    }
}
