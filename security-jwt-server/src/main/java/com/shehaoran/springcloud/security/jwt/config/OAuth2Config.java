package com.shehaoran.springcloud.security.jwt.config;

import com.shehaoran.springcloud.security.jwt.JWTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * ClientId、Client-Secret：这两个参数对应请求端定义的 cleint-id 和 client-secret
 * <p>
 * authorizedGrantTypes 可以包括如下几种设置中的一种或多种：
 * <p>
 * authorization_code：授权码类型。
 * implicit：隐式授权类型。
 * password：资源所有者（即用户）密码类型。
 * client_credentials：客户端凭据（客户端ID以及Key）类型。
 * refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {


    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService kiteUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private DataSource dataSource;

    private TokenEnhancer jwtTokenEnhancer;


    /**
     * 调用此方法才能支持 password 模式。
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        /**
         * jwt 增强模式
         */
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(new JWTokenEnhancer());
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        /**
         * redis token 方式
         * 调用此方法才能支持 password 模式。
         */
        endpoints.tokenStore(jwtTokenStore)
                .userDetailsService(kiteUserDetailsService)
                /**
                 * 支持 password 模式
                 */
                .authenticationManager(authenticationManager)
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);

    }

    /*@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("order-client")
                .secret(passwordEncoder.encode("order-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all")
                .and()
                .withClient("user-client")
                .secret(passwordEncoder.encode("user-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all");
    }*/

    /**
     * ClientDetailsServiceConfigurer参数的重写，在这里定义各个端的约束条件。包括
     * ClientId、Client-Secret：这两个参数对应请求端定义的 cleint-id 和 client-secret
     * <p>
     * authorizedGrantTypes 可以包括如下几种设置中的一种或多种：
     * <p>
     * authorization_code：授权码类型。
     * implicit：隐式授权类型。
     * password：资源所有者（即用户）密码类型。
     * client_credentials：客户端凭据（客户端ID以及Key）类型。
     * refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
     * accessTokenValiditySeconds：token 的有效期
     * <p>
     * scopes：用来限制客户端访问的权限，在换取的 token 的时候会带上 scope 参数，只有在 scopes 定义内的，才可以正常换取 token。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsServiceBuilder jcsb = clients.jdbc(dataSource);
        jcsb.passwordEncoder(passwordEncoder);
    }

    /**
     * POST /oauth/authorize  授权码模式认证授权接口
     * GET/POST /oauth/token  获取 token 的接口
     * POST  /oauth/check_token  检查 token 合法性接口
     */

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //第一行代码是允许客户端访问 OAuth2 授权接口，否则请求 token 会返回 401。
        security.allowFormAuthenticationForClients();
        //第二行和第三行分别是允许已授权用户访问 checkToken 接口和获取 token 接口。
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密方式 seccret
        String encode = bCryptPasswordEncoder.encode("web-client123456");
        boolean matches = bCryptPasswordEncoder.matches("d2ViLWNsaWVudCUzQXdlYi1jbGllbnQxMjM0NTY=",
                "$2a$10$spVVqOIgkNrG.5Yax2KW8eIGAqjJnShYg5hDFcfUN55QD5sNth5Nm");
        System.out.println();
        System.out.println(encode);
    }
}