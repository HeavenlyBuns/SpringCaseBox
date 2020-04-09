package com.shehaoran.springcloud.cloudgetway.rate.request_rate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteAddrKeyResolver.class);
    public static final String BEAN_NAME = "remoteAddrKeyResolver";
    /**
     * 我们实现一个使用请求 IP 作为限流键的 KeyResolver
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        LOGGER.info("获取令牌桶,ip = {}",hostAddress);
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
