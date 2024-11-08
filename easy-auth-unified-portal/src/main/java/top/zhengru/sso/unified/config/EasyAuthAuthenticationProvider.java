package top.zhengru.sso.unified.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import top.zhengru.sso.unified.service.UserService;
import top.zhengru.sso.unified.service.impl.UserDetailImpl;

@Component
public class EasyAuthAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Long userId = (Long) authentication.getPrincipal();
        UserDetailImpl userDetails = userService.queryUserDetailById(userId);
        if (userDetails == null) {
            return null;
        }
        return new EasyAuthAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(EasyAuthAuthenticationToken.class);
    }
}
