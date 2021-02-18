package store.facade.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.data.user.UserViewData;
import store.facade.converter.Converter;
import store.facade.user.UserFacade;
import store.model.BillingAddress;
import store.model.User;
import store.model.UserRole;
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
    public void insertUser(UserData userData, UserRole userRole) {
        User user = reverseConverter.convert(userData);
        userService.insertUser(user, userRole);
    }

    @Override
    public List<UserData> getAll() {

        List<User> users = userService.getAll();
        return converter.convertAll(users);
    }

    @Override
    public List<UserData> getByRole(Enum role) {

        List<User> users = userService.getByRole(role);
        return converter.convertAll(users);
    }

    @Override
    public void updateUserAccount(String email, UserViewData userViewData) {

        User user = viewReverseConverter.convert(userViewData);
        user.setEmail(email);
        userService.update(user);
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
