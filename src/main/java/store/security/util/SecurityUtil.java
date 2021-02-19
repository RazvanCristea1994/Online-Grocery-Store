package store.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import store.model.UserRole;

import java.util.Optional;

@Component
public class SecurityUtil {

    public static final String SHOPPING_CART_SESSION_ATTRIBUTE_NAME = "SHOPPING-CART";

    public String getRedirectUrlAfterLoginDependingOnAuthentication(Authentication authentication) {

        Optional<UserRole> authority = authentication.getAuthorities()
                .stream()
                .findFirst()
                .map(Object::toString)
                .map(UserRole::valueOf);
        return authority.map(this::getRedirectUrlAfterLoginDependingOnUserRole).orElse("");
    }

    private String getRedirectUrlAfterLoginDependingOnUserRole(UserRole userRole){

        if (userRole == UserRole.ADMIN){
            return "/products";
        }
        return "/";
    }
}
