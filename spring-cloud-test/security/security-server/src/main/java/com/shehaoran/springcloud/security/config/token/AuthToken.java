package com.shehaoran.springcloud.security.config.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthToken extends AbstractAuthenticationToken {

    /**
     * 账号
     */
    protected String principal;

    public AuthToken() {
        super(null);

    }



    public AuthToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
