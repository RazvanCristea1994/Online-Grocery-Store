package store.dao.user.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.user.UserDao;
import store.model.User;
import store.model.UserRole;


import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends HibernateAbstractCrudRepository<String, User> implements UserDao {

    @Override
    public List<User> getByRole(Enum askedRole) {

        Session session = super.getCurrentSession();
        String queryString = "FROM " + this.getValueClass().getName() + " user WHERE user.role=:role";
        Query<User> query = session.createQuery(queryString, this.getValueClass());
        query.setParameter("role", askedRole);
        return query.list();
    }
 
    @Override
    public Optional<User> getByEmail(String email) {

        Session session = super.getCurrentSession();
        return Optional.ofNullable(session.get(this.getValueClass(), email));
    }

    @Override
    public Optional<User> getByPhoneNumber(String searchedPhoneNumber) {

        String queryString = "FROM " + getValueClass().getName() + " WHERE phoneNumber=:phoneNumber";
        Query<User> query = super.getCurrentSession().createQuery(queryString, User.class);
        query.setParameter("phoneNumber", searchedPhoneNumber);
        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    public List<String> getAllUserEmailsByRole(UserRole givenRole) {

        String queryString ="SELECT user.email FROM " + getValueClass().getName() + " user WHERE user.role=:role";
        Query<String> query = super.getCurrentSession().createQuery(queryString, String.class);
        query.setParameter("role", givenRole);
        return query.list();
    }

    @Override
    protected Class<User> getValueClass() {
        return User.class;
    }
}
