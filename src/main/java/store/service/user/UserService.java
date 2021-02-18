package store.service.user;

import store.model.BillingAddress;
import store.model.User;
import store.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void insertUser(User user, UserRole userRole);

    List<User> getAll();

    List<User> getByRole(Enum role);

    void update(User user);

    Optional<User> getByEmail(String email);

    Optional<User> getByPhoneNumber(String phoneNumber);

    Optional<BillingAddress> getBillingAddressById(Long id);

    void delete(String email);


}
