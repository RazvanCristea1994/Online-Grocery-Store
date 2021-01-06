package store.facade.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserViewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserViewConverter implements Converter<User, UserViewData> {

    @Autowired
    private Populator<User, UserViewData> userViewPopulator;

    @Override
    public UserViewData convert(User user) {

        UserViewData userViewData = new UserViewData();
        userViewPopulator.populate(userViewData, user);
        return userViewData;
    }
}
