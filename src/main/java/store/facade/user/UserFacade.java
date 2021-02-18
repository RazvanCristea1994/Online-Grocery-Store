package store.facade.user;

import store.data.user.UserData;
import store.data.user.UserViewData;
import store.model.BillingAddress;
import store.model.User;
import store.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    void insertUser(UserData userData, UserRole userRole);

    List<UserData> getAll();

    List<UserData> getByRole(Enum role);

    void updateUserAccount(String email, UserViewData userViewData);

    void delete(String email);

    Optional<User> getByEmail(String email);

    Optional<BillingAddress> getBillingAddressById(Long id);

    Optional<User> getByPhoneNumber(String phoneNumber);
}
