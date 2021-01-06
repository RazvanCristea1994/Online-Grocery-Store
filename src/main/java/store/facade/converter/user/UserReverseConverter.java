package store.facade.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserReverseConverter implements Converter<UserData, User> {

    @Autowired
    private Populator<UserData, User> userReversePopulator;

    @Override
    public User convert(UserData userData) {

        User user = new User();
        userReversePopulator.populate(user, userData);
        return user;
    }
}
