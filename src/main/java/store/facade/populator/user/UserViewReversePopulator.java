package store.facade.populator.user;

import org.springframework.stereotype.Component;
import store.data.user.UserViewData;
import store.facade.populator.Populator;
import store.model.BillingAddress;
import store.model.User;

@Component
public class UserViewReversePopulator implements Populator<UserViewData, User> {

    @Override
    public void populate(User user, UserViewData userViewData) {

        user.setFirstName(userViewData.getFirstName());
        user.setLastName(userViewData.getLastName());
        user.setPhoneNumber(userViewData.getPhoneNumber());

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCountry(userViewData.getCountry());
        billingAddress.setCity(userViewData.getCity());
        billingAddress.setCounty(userViewData.getCounty());
        billingAddress.setStreetName(userViewData.getStreetName());
        billingAddress.setStreetNumber(userViewData.getStreetNumber());
        billingAddress.setFlatNumber(userViewData.getFlatNumber());
        billingAddress.setBuildingNumber(userViewData.getBuildingNumber());

        user.setBillingAddress(billingAddress);
    }
}
