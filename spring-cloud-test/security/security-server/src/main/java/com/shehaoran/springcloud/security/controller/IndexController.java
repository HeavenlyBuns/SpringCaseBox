package com.shehaoran.springcloud.security.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "auth ok....";

    }

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public User getUser(Principal principal, Authentication authentication) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(principal.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return (User)authentication.getPrincipal();
    }

    /*@RequestMapping({"/oauth/check_token"})
    @ResponseBody
    public Map<String, ?> checkToken(@RequestParam("token") String value) {
        OAuth2AccessToken token = this.resourceServerTokenServices.readAccessToken(value);
        if (token == null) {
            throw new InvalidTokenException("Token was not recognised");
        } else if (token.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        } else {
            OAuth2Authentication authentication = this.resourceServerTokenServices.loadAuthentication(token.getValue());
            Map<String, ?> response = this.accessTokenConverter.convertAccessToken(token, authentication);
            return response;
        }
    }*/

}
