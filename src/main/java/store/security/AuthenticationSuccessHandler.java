package store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import store.data.order.ShoppingCartItemRequestData;
import store.facade.order.ShoppingCartItemFacade;
import store.security.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private ShoppingCartItemFacade shoppingCartItemFacade;

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        HttpSession session = request.getSession();
        Object shoppingCart = session.getAttribute(SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME);
        if (shoppingCart != null) {
            String email = authentication.getName();
            mergeShoppingCart((List<ShoppingCartItemRequestData>) shoppingCart, email);
            session.removeAttribute(SecurityUtil.SHOPPING_CART_SESSION_ATTRIBUTE_NAME);
        }
        return securityUtil.getRedirectUrlAfterLoginDependingOnAuthentication(authentication);
    }

    private void mergeShoppingCart(List<ShoppingCartItemRequestData> shoppingCart, String email) {

        shoppingCart.forEach(productItemData -> addProductItemData(email, productItemData));
    }

    private void addProductItemData(String email, ShoppingCartItemRequestData shoppingCartItemRequestData) {

        try {
            shoppingCartItemFacade.addShoppingCartItem(shoppingCartItemRequestData,email);
        } catch (IllegalArgumentException ignored){ }
    }
}
