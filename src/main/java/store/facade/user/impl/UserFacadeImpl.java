package store.facade.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.data.user.UserViewData;
import store.facade.converter.Converter;
import store.facade.user.UserFacade;
import store.model.BillingAddress;
import store.model.User;
import store.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserData> converter;

    @Autowired
    private Converter<UserData, User> reverseConverter;

    @Autowired
    private Converter<UserViewData, User> viewReverseConverter;

    @Override
    public void insertAdmin(UserData userData) {
        User user = reverseConverter.convert(userData);
        userService.insertAdmin(user);
    }

    @Override
    public void insertCustomer(UserData userData) {
        userService.insertCustomer(reverseConverter.convert(userData));
    }

    @Override
    public List<UserData> getAll() {

        List<User> users = userService.getAll();
        return converter.convertAll(users);
    }

    @Override
    public void updateUserAccount(String email, UserViewData userViewData) {

        User user = viewReverseConverter.convert(userViewData);
        user.setEmail(email);
        Optional<User> oldUser = userService.getByEmail(email);
        oldUser.ifPresent(
                foundUser -> {
                    user.setPassword(oldUser.get().getPassword());
                    user.setRole(oldUser.get().getRole());
                    BillingAddress billingAddress = user.getBillingAddress();
                    billingAddress.setId(oldUser.get().getBillingAddress().getId());
                    user.setBillingAddress(billingAddress);
                    userService.update(user);
                }
        );
    }

    @Override
    public void delete(String email) {
        userService.delete(email);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    public Optional<BillingAddress> getBillingAddressById(Long id) {
        return userService.getBillingAddressById(id);
    }

    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) {
        return userService.getByPhoneNumber(phoneNumber);
    }
}
