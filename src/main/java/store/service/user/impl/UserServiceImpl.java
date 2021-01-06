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
    public void insertAdmin(User user) {

        Optional<User> optionalUser = userDao.getByEmail(user.getEmail());
        optionalUser.ifPresentOrElse(
                foundUser -> {
                    throw new DataIntegrityViolationException("Invalid email");
                },
                () -> {
                    user.setRole(UserRole.ADMIN);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userDao.save(user);
                }
        );
    }

    @Override
    public void insertCustomer(User user) {

        Optional<User> optionalUser = userDao.getByEmail(user.getEmail());
        optionalUser.ifPresentOrElse(
                foundUser -> {
                    throw new DataIntegrityViolationException("Invalid email");
                },
                () -> {
                    user.setRole(UserRole.CUSTOMER);
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
    public void update(User user) {

        Optional<User> optionalUser = userDao.getByEmail(user.getEmail());
        optionalUser.ifPresentOrElse(
                foundUser -> {
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
        optionalUser.ifPresent(o -> userDao.delete(email));
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
