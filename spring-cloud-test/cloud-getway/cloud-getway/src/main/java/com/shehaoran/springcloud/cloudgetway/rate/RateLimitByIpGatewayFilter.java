package com.shehaoran.springcloud.cloudgetway.rate;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @title: Shr
 * @description: 限流
 * @date 2020/4/9 10:48
 */
public class RateLimitByIpGatewayFilter implements GatewayFilter, Ordered {
    private static final Map<String,Bucket> CACHE = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitByIpGatewayFilter.class);
    int capacity;
    int refillTokens;
    Duration refillDuration;

    public RateLimitByIpGatewayFilter(int capacity, int refillTokens, Duration refillDuration) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillDuration = refillDuration;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip  = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = CACHE.computeIfAbsent(ip,k -> createNewBucket());

        LOGGER.info("获取令牌桶,IP: " + ip + "，TokenBucket Available Tokens: " + bucket.getAvailableTokens());
        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }

    }

    private Bucket createNewBucket() {
        Refill refill = Refill.of(refillTokens,refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity,refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
