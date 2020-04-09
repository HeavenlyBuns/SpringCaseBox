package com.shehaoran.springcloud.cloudgetway.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/9 16:28
 */
@Component
public class RateLimitByCpuGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitByCpuGatewayFilter.class);
    @Autowired
    private MetricsEndpoint metricsEndpoint;

    private static final String METRIC_NAME = "system.cpu.usage";
    private static final double MAX_USAGE = 0.50D;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<MetricsEndpoint.Sample> measurements = metricsEndpoint.metric(METRIC_NAME, null).getMeasurements();
        Double systemCpuUsage = measurements.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(MetricsEndpoint.Sample::getValue)
                .filter(Double::isFinite)
                .orElse(0.0D);
        boolean ok = systemCpuUsage < MAX_USAGE;
        LOGGER.info("system.cpu.usage: " + systemCpuUsage + " ok: " + ok);
        if (!ok) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            LOGGER.warn("根据CPU负载动态限流,达到阈值,拒绝访问,返回429!");
            return exchange.getResponse().setComplete();
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
