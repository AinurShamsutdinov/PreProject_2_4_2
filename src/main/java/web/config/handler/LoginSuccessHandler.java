package web.config.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@Configuration
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        boolean hasUserRole = false;
        boolean hasAdminRole = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                hasAdminRole = true;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                hasUserRole = true;
            }
        }

        if (hasAdminRole) {
            httpServletResponse.sendRedirect("/admin");
        } else if (hasUserRole) {
            httpServletResponse.sendRedirect("/userpage");
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}