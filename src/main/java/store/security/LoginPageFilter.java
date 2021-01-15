package store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import store.security.util.SecurityUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@Component
public class LoginPageFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String contextPath = httpServletRequest.getContextPath();
            String relativeRedirectUrl = securityUtil.getRedirectUrlAfterLoginDependingOnAuthentication(authentication);
            httpServletResponse.sendRedirect(contextPath + relativeRedirectUrl);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
