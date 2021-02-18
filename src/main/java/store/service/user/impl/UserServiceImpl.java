package store.service.user.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.dao.user.BillingAddressDao;
import store.dao.user.UserDao;
import store.model.BillingAddress;
import store.model.User;
import store.model.UserRole;
import store.service.user.UserService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BillingAddressDao billingAddressDao;

    @Override
    public void insertUser(User user, UserRole userRole) {

        Optional<User> optionalUserCheckEmail = userDao.getByEmail(user.getEmail());
        Optional<User> optionalUserCheckPhoneNumber = userDao.getByPhoneNumber(user.getPhoneNumber());

        optionalUserCheckEmail.ifPresent(
                foundUser -> {
                    throw new DataIntegrityViolationException("This email already exist");
                }
        );

        optionalUserCheckPhoneNumber.ifPresentOrElse(
                foundUser -> {
                    throw new DataIntegrityViolationException("This phone number already exist");
                },
                () -> {
                    user.setRole(userRole);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userDao.save(user);
                }
        );
    }

    @Override
    public List<User> getAll() {

        List<User> users = userDao.getAll();
        users.forEach(user -> Hibernate.initialize(user.getBillingAddress()));
        return users;
    }

    @Override
    public List<User> getByRole(Enum role) {

        List<User> users = userDao.getByRole(role);
        users.forEach(user -> Hibernate.initialize(user.getBillingAddress()));
        return users;
    }

    @Override
    public void update(User user) {

        Optional<User> oldUser = userDao.getByEmail(user.getEmail());
        Optional<User> optionalUser = userDao.getByPhoneNumber(user.getPhoneNumber());
        if (!optionalUser.isEmpty() && !oldUser.equals(optionalUser)){
            throw new IllegalArgumentException("Phone number already used");
        }
        oldUser.ifPresentOrElse(
                foundUser -> {
                    user.setPassword(oldUser.get().getPassword());
                    user.setRole(oldUser.get().getRole());
                    BillingAddress billingAddress = user.getBillingAddress();
                    billingAddress.setId(oldUser.get().getBillingAddress().getId());
                    user.setBillingAddress(billingAddress);
                    billingAddressDao.update(user.getBillingAddress());

                    userDao.update(user);
                },
                () -> {
                    throw new IllegalArgumentException("Invalid user");
                }
        );
    }

    @Override
    public Optional<User> getByEmail(String email) {

        Optional<User> optionalUser = userDao.getByEmail(email);
        optionalUser.ifPresent(
                user -> Hibernate.initialize(user.getBillingAddress())
        );
        return optionalUser;
    }

    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) {

        return userDao.getByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<BillingAddress> getBillingAddressById(Long id) {

        return billingAddressDao.getById(id);
    }

    @Override
    public void delete(String email) {

        Optional<User> optionalUser = userDao.getByEmail(email);
        optionalUser.ifPresentOrElse(o -> userDao.delete(email), () -> {
            throw new NoSuchElementException("Error. The account was not deleted");
        });
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDao.getByEmail(email);
        UserDetails userDetails;
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(foundUser.getRole().toString()));
            userDetails = new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return userDetails;
    }
}
