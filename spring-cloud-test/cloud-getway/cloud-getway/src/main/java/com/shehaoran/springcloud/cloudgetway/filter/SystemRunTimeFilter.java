package com.shehaoran.springcloud.cloudgetway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 * @title: Shr
 * @description: 自定义过滤器需要实现 GatewayFilter 和 Ordered。
 * 其中 GatewayFilter 中的这个方法就是用来实现你的自定义的逻辑的
 * @date 2020/4/8 16:19
 */
public class SystemRunTimeFilter implements GatewayFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRunTimeFilter.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 往 ServerWebExchange 中放入了一个属性 elapsedTimeBegin，属性值为当时的毫秒级时间戳
         */
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());

        return chain.filter(exchange)
                /**
                 * 其实就是 chain.filter(exchange) 之前的就是 “pre” 部分，之后的也就是 then 里边的是 “post” 部分。
                 */
                .then(Mono.fromRunnable(() -> {
                    /**
                     * 在请求执行结束后，又从中取出我们之前放进去的那个时间戳
                     */
                    Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                    if (startTime != null) {
                        LOGGER.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
