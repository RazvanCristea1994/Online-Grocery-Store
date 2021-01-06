package store.facade.populator.user;

import org.springframework.stereotype.Component;
import store.data.user.UserViewData;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserViewPopulator implements Populator<User, UserViewData> {

    @Override
    public void populate(UserViewData userViewData, User user) {

        userViewData.setFirstName(user.getFirstName());
        userViewData.setLastName(user.getLastName());
        userViewData.setPhoneNumber(user.getPhoneNumber());
        userViewData.setCountry(user.getBillingAddress().getCountry());
        userViewData.setCity(user.getBillingAddress().getCity());
        userViewData.setCounty(user.getBillingAddress().getCounty());
        userViewData.setStreetName(user.getBillingAddress().getStreetName());
        userViewData.setStreetNumber(user.getBillingAddress().getStreetNumber());
        userViewData.setFlatNumber(user.getBillingAddress().getFlatNumber());
        userViewData.setBuildingNumber(user.getBillingAddress().getBuildingNumber());
    }
}
