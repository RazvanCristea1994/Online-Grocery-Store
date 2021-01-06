package store.facade.user;

import store.data.user.UserData;
import store.data.user.UserViewData;
import store.model.BillingAddress;
import store.model.User;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    void insertAdmin(UserData userData);

    void insertCustomer(UserData userData);

    List<UserData> getAll();

    void updateUserAccount(String email, UserViewData userViewData);

    void delete(String email);

    Optional<User> getByEmail(String email);

    Optional<BillingAddress> getBillingAddressById(Long id);

    Optional<User> getByPhoneNumber(String phoneNumber);
}
