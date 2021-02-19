package store.service.order.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dao.order.OrderDao;
import store.dao.order.OrderItemDao;
import store.dao.order.ShoppingCartItemDao;
import store.dao.product.ProductDao;
import store.dao.user.UserDao;
import store.event.MailEventPublisher;
import store.event.mailevent.MailEvent;
import store.event.mailevent.PlacedOrderMailEvent;
import store.model.*;
import store.service.order.OrderService;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShoppingCartItemDao shoppingCartItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    MailEventPublisher mailEventPublisher;

    @Override
    public List<Order> getOrdersByUserEmail(String email) {

        List<Order> orders = orderDao.getOrdersByUserEmail(email);
        orders.forEach(order -> Hibernate.initialize(order.getOrderItems()));
        return orders;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order placeOrder(String email) {
        Order order = new Order();
        makeOrder(email, order);
        orderDao.save(order);
        List<OrderItem> orderItems = getOrderItems(email, order);
        order.setOrderItems(orderItems);
        String[] to = new String[]{order.getUser().getEmail()};
        MailEvent mailEvent = new PlacedOrderMailEvent(this, order, to);
        mailEventPublisher.publish(mailEvent);
        return order;
    }

    @Override
    public void refactorShippingCart(String email) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemDao.getByUserEmail(email);
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            Optional<Product> product = productDao.getById(shoppingCartItem.getProduct().getId());
            if (product.isPresent()) {
                if (shoppingCartItem.getQuantity() > product.get().getStock()) {
                    if (product.get().getStock().equals(0)) {
                        shoppingCartItemDao.deleteByProductIdAndUserEmail(shoppingCartItem.getProduct().getId(), email);
                    } else {
                        shoppingCartItem.setQuantity(product.get().getStock());
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid product!");
            }
        }
    }

    private List<OrderItem> getOrderItems(String email, Order order) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemDao.getByUserEmail(email);
        if (shoppingCartItems.isEmpty()) {
            throw new IllegalArgumentException("The shopping cart is empty!");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            Optional<Product> product = productDao.getById(shoppingCartItem.getProduct().getId());
            if (product.isPresent()) {
                if (shoppingCartItem.getQuantity() <= product.get().getStock()) {
                    OrderItem orderItem = new OrderItem();
                    getOrderItemFromShoppingCartItem(orderItem, shoppingCartItem);
                    order.setTotalCost(order.getTotalCost() + orderItem.getPrice() * orderItem.getQuantity());
                    orderItem.setOrder(order);
                    orderItemDao.save(orderItem);
                    orderItems.add(orderItem);
                    product.get().setStock(product.get().getStock() - shoppingCartItem.getQuantity());
                    shoppingCartItemDao.deleteByProductIdAndUserEmail(shoppingCartItem.getProduct().getId(), email);
                } else {
                    throw new IllegalArgumentException("The product " + product.get().getName() + " is currently out of stock or has insufficient stock.");
                }
            } else {
                throw new IllegalArgumentException("Invalid product!");
            }
        }
        return orderItems;
    }

    private void makeOrder(String email, Order order) {
        Optional<User> user = userDao.getByEmail(email);
        if (user.isPresent()) {
            order.setUser(user.get());
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setTotalCost(0.0);
    }

    private void getOrderItemFromShoppingCartItem(OrderItem orderItem, ShoppingCartItem shoppingCartItem) {
        orderItem.setPrice(shoppingCartItem.getProduct().getPrice());
        orderItem.setQuantity(shoppingCartItem.getQuantity());
        orderItem.setProduct(shoppingCartItem.getProduct());
    }
}
