package store.dao.user;

import store.model.BillingAddress;

import java.util.Optional;

public interface BillingAddressDao {

    void save(BillingAddress billingAddress);

    Optional<BillingAddress> getById(Long id);

    void update(BillingAddress billingAddress);

    void delete(Long id);
}
