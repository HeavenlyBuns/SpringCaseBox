package com.shehaoran.springcloud.security.jwt.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @BelongsPackage: com.syhj.mall.security.provider
 * @Author: zsx
 * @CreateTime: 2019-10-12 15:20
 * @Description: 自定义 AuthenticationProvider， 以使用自定义的 MyAuthenticationToken
 */
public abstract class AbstractUserDetailsAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);

    @Override
    public final void afterPropertiesSet() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() == null?"NONE_PROVIDED":authentication.getName();
        UserDetails user;

        try {
            user = this.retrieveUser(username, authentication);
        } catch (UsernameNotFoundException var6) {
            log.debug("User \'" + username + "\' not found");

            throw var6;
        }

        return this.createSuccessAuthentication(authentication, user);
    }

    protected abstract Authentication createSuccessAuthentication(Authentication authentication, UserDetails user);


    protected abstract UserDetails retrieveUser(String username, Authentication authentication) throws AuthenticationException;



}
