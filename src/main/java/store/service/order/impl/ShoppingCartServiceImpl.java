package store.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dao.order.ShoppingCartDao;
import store.dao.user.UserDao;
import store.model.ShoppingCart;
import store.model.User;
import store.service.order.ShoppingCartService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ShoppingCart getOrCreateShoppingCartByUserEmail(String email) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByUserEmail(email);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            Optional<User> user = userDao.getByEmail(email);
            if(user.isEmpty()) {
                throw new IllegalArgumentException("Invalid email!");
            }
            shoppingCart.setUser(user.get());
            shoppingCartDao.save(shoppingCart);
        }
        return shoppingCart;
    }
}
