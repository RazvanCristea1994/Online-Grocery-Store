package store.facade.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserConverter implements Converter<User, UserData> {

    @Autowired
    private Populator<User, UserData> userPopulator;

    @Override
    public UserData convert(User user) {

        UserData userData = new UserData();
        userPopulator.populate(userData, user);
        return userData;
    }
}
