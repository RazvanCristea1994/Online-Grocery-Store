package store.service.user;

import store.model.BillingAddress;
import store.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void insertAdmin(User user);

    void insertCustomer(User user);

    List<User> getAll();

    void update(User user);

    Optional<User> getByEmail(String email);

    Optional<User> getByPhoneNumber(String phoneNumber);

    Optional<BillingAddress> getBillingAddressById(Long id);

    void delete(String email);


}
