package store.facade.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.user.UserViewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.User;

@Component
public class UserViewReverseConverter implements Converter<UserViewData, User> {

    @Autowired
    private Populator<UserViewData, User> userViewReversePopulator;

    @Override
    public User convert(UserViewData userViewData) {

        User user = new User();
        userViewReversePopulator.populate(user, userViewData);
        return user;
    }
}
