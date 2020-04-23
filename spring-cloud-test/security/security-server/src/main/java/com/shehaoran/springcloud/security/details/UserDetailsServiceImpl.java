package com.shehaoran.springcloud.security.details;

import com.shehaoran.springcloud.security.entity.Account;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 定义UserDetailService实现
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
    private PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(this.passwordEncoder.encode("123456"));
        return new User(username, account.getPassword(), account.isEnabled(),
                account.isAccountNonExpired(),
                account.isCredentialsNonExpired(),
                account.isAccountNonLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
