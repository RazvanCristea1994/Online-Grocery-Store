package store.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class HibernateAbstractCrudRepository<ID extends Serializable, T> implements CrudRepository <ID, T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected abstract Class<T> getValueClass();

    @Override
    public void save(T value){

        Session session = this.getCurrentSession();
        session.saveOrUpdate(value);
    }

    @Override
    public void update(T value){

        Session session = this.getCurrentSession();
        session.merge(value);
    }

    @Override
    public void delete(ID id){

        Session session = this.getCurrentSession();
        T entity = session.get(this.getValueClass(), id);
        if (entity != null) {
            session.delete(entity);
        }
    }

    @Override
    public Optional<T> getById(ID id){

        Session session = this.getCurrentSession();
        return Optional.ofNullable(session.get(this.getValueClass(), id));
    }

    @Override
    public List<T> getAll(){

        Session session = this.getCurrentSession();
        Class<T> valueClass = this.getValueClass();
        List list = session.createQuery("FROM " + valueClass.getName(), valueClass).list();
        return list;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
