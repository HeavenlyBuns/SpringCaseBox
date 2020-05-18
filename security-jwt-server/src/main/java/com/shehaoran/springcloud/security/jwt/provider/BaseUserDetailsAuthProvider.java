package com.shehaoran.springcloud.security.jwt.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * description: BaseUserDetailsAuthProvider <br>
 * date: 2020/5/15 15:31 <br>
 * author: she.haoran <br>
 * version: 1.0 <br>
 */
@Component
public class BaseUserDetailsAuthProvider extends AbstractUserDetailsAuthenticationProvider{
    @Override
    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
         System.out.println();
        return null;
    }

    @Override
    protected UserDetails retrieveUser(String username, Authentication authentication) throws AuthenticationException {
        System.out.println();
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AbstractAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
