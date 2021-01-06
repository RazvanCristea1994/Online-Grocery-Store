package store.facade.populator.user;

import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserPopulator implements Populator<User, UserData> {

    @Override
    public void populate(UserData userData, User user) {

        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setRole(user.getRole());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setPhoneNumber(user.getPhoneNumber());
        userData.setAddressId(Long.toString(user.getBillingAddress().getId()));
        userData.setCountry(user.getBillingAddress().getCountry());
        userData.setCity(user.getBillingAddress().getCity());
        userData.setCounty(user.getBillingAddress().getCounty());
        userData.setStreetName(user.getBillingAddress().getStreetName());
        userData.setStreetNumber(user.getBillingAddress().getStreetNumber());
        userData.setFlatNumber(user.getBillingAddress().getFlatNumber());
        userData.setBuildingNumber(user.getBillingAddress().getBuildingNumber());
    }
}
