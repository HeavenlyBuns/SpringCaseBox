package com.shehaoran.springcloud.security.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){

        return "auth ok ....";
    }

    /**
     * 一个 RESTful 方法，只有当访问用户具有 ROLE_ADMIN 权限时才能访问，否则返回 401 未授权。
     *
     * 通过 Authentication 参数或者 SecurityContextHolder.getContext().getAuthentication() 可以拿到授权信息进行查看。
     */
    @GetMapping(value = "get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String token = details.getTokenValue();
        return token;
    }



}
