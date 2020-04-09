package com.shehaoran.springcloud.gatewayrate.rate;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/9 14:17
 */
public class RemoteAddrKeyResolver implements KeyResolver {

    public static final String BEAN_NAME = "remoteAddrKeyResolver";
    /**
     * 我们实现一个使用请求 IP 作为限流键的 KeyResolver
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
