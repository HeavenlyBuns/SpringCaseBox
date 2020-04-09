package com.shehaoran.springcloud.cloudgetway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @title: Shr
 * @description: 自定义过滤器工厂,需在application.yml 设置 default-filters: -Elapsed=true
 * filters:
 * - StripPrefix=1
 * - AddResponseHeader=X-Response-Default-Foo, Default-Bar
 * @date 2020/4/8 16:59
 */
//@Component
public class CustomGatewayFactory extends AbstractGatewayFilterFactory<CustomGatewayFactory.Config> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomGatewayFactory.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
    private static final String KEY = "withParams";


    public CustomGatewayFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(ELAPSED_TIME_BEGIN,System.currentTimeMillis());
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                        if (startTime != null) {
                            LOGGER.info("==========================================================================");
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");

                            if (config.isWithParams()) {
                                sb.append(" 接收到 Config params:").append(exchange.getRequest().getQueryParams());
                            }
                            LOGGER.info(sb.toString());
                        }

                    }));
        };
    }

    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "withParams=" + withParams +
                    '}';
        }
    }

}
