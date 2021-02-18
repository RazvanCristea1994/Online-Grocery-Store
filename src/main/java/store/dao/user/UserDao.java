package store.dao.user;

import store.model.User;
import store.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void save(User value);

    List<User> getAll();

    List<User> getByRole(Enum role);

    Optional<User> getByEmail(String email);

    Optional<User> getByPhoneNumber(String phoneNumber);

    List<String> getAllUserEmailsByRole(UserRole role);

    void update(User user);

    void delete(String email);
}
