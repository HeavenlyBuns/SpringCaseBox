package com.shehaoran.springcloud.security.config.token;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AdminAuthTokenParser implements AuthenticationTokenParser {

    @Override
    public AbstractAuthenticationToken parse(String authenticationTokenStr) {
        return JSONObject.parseObject(authenticationTokenStr, AbstractAuthenticationToken.class);
    }

    }