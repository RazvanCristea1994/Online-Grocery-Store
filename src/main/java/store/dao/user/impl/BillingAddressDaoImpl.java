package store.dao.user.impl;

import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.user.BillingAddressDao;
import store.model.BillingAddress;

@Repository
public class BillingAddressDaoImpl extends HibernateAbstractCrudRepository<Long, BillingAddress> implements BillingAddressDao {


    @Override
    protected Class<BillingAddress> getValueClass() {
        return BillingAddress.class;
    }
}
