package com.shehaoran.springcloud.security.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 它是实现用户身份验证的一种方式，也是最简单方便的一种。另外还有结合 AuthenticationProvider的方式，
 */
@Service
public class AccountDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 账号 admin ，密码 123456，稍后在换取 token 的时候会用到。并且给这个用户设置 "ROLE_ADMIN" 角色
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("usernameis:" + username);
        // 查询数据库操作
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("the user is not found");
        } else {
            // 用户角色也应在数据库中获取
            String role = "user>info>delete";

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            // 线上环境应该通过用户名查询数据库获取加密后的密码
            String password = passwordEncoder.encode("123456");
            return new User(username, password, authorities);
        }
    }


}
