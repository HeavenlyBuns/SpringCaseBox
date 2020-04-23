package com.shehaoran.springcloud.security.config.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface AuthenticationTokenParser {

    AbstractAuthenticationToken parse(String authenticationTokenStr);

}
