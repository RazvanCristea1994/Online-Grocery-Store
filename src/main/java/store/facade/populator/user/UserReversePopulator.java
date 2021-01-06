package store.facade.populator.user;

import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.facade.populator.Populator;
import store.model.BillingAddress;
import store.model.User;

@Component
public class UserReversePopulator implements Populator<UserData, User> {

    @Override
    public void populate(User user, UserData userData) {

        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setRole(userData.getRole());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setPhoneNumber(userData.getPhoneNumber());

        Long id = null;
        if (userData.getAddressId() != null && !userData.getAddressId().isBlank()){
            id = Long.parseLong(userData.getAddressId());
        }
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setId(id);
        billingAddress.setCountry(userData.getCountry());
        billingAddress.setCity(userData.getCity());
        billingAddress.setCounty(userData.getCounty());
        billingAddress.setStreetName(userData.getStreetName());
        billingAddress.setStreetNumber(userData.getStreetNumber());
        billingAddress.setFlatNumber(userData.getFlatNumber());
        billingAddress.setBuildingNumber(userData.getBuildingNumber());

        user.setBillingAddress(billingAddress);
    }
}
