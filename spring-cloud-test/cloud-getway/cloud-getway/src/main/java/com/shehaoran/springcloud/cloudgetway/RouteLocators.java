package com.shehaoran.springcloud.cloudgetway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/8 15:09
 */
//@Component
public class RouteLocators {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        /**
         * 新增的 StripPrefix 可以接受一个非负整数，对应的具体实现是 StripPrefixGatewayFilterFactory，
         * 从名字就可以看出它的作用是去掉前缀的，那个整数即对应层数。具体到本例中，
         * 我们通过 Spring Cloud Gateway 访问 /customer/hello/windmt，
         * 那么当网关服务向后转发请求时，会去掉 /customer，微服务收到的就是 /hello/windmt。
         */
        return builder.routes()
                .route(r -> r.path("/eureka-producer/**")
//                        .filters(f -> f.stripPrefix(2)
//                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://EUREKA-PRODUCER/**")
                        .order(0)
                        .id("fluent_customer_service")
                )
                .build();
        // @formatter:on
    }

}
