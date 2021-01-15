package store.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutRequestSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String contextPath = httpServletRequest.getContextPath();
        if (authentication != null) {
            httpServletResponse.sendRedirect(contextPath + "?message=You have been logged out");
            return;
        }
        httpServletResponse.sendRedirect(contextPath + "/");
    }
}
