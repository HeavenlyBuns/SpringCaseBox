package com.heavenlybuns.springsecurity.config;

import com.heavenlybuns.springsecurity.entity.AccountEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/1/19 17:20
 */
@Service
public class AccountDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("test".equals(username)) {
            return new AccountEntity(username,"test");
        } else {
            throw new UsernameNotFoundException("用户名未找到");
        }

    }
}
