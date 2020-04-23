package com.shehaoran.springcloud.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private LoginAuthenticationFilter loginAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors().disable().cors().and().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
//                .addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/webjars/**",
                        "/swagger/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/captcha.jpg",
                        "/file/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();//配置所有访问控制，必须认证过后才可以访问
    }


}
