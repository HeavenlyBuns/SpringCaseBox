package com.shehaoran.springcloud.cloudgetway;

import com.shehaoran.springcloud.cloudgetway.actuator.RateLimitByCpuGatewayFilter;
import com.shehaoran.springcloud.cloudgetway.filter.SystemRunTimeFilter;
import com.shehaoran.springcloud.cloudgetway.rate.RateLimitByIpGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author Administrator
 * @title: Shr
 * @description: 可通过配置文件和实现bean
 * @date 2020/4/8 15:09
 */
@Component
public class RouteLocators {


    @Autowired
    private RateLimitByCpuGatewayFilter rateLimitByCpuGatewayFilter;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        /**
         * 新增的 StripPrefix 可以接受一个非负整数，对应的具体实现是 StripPrefixGatewayFilterFactory，
         * 从名字就可以看出它的作用是去掉前缀的，那个整数即对应层数。具体到本例中，
         * 我们通过 Spring Cloud Gateway 访问 /customer/hello/windmt，
         * 那么当网关服务向后转发请求时，会去掉 /customer，微服务收到的就是 /hello/windmt。
         */
        return builder.routes()
                .route(r -> r.path("/trace-a/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(new SystemRunTimeFilter())
                                .filter(rateLimitByCpuGatewayFilter)
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://TRACE-A")
                        .order(0)
                        .id("trace_customer_service")
                )
                .build();
    }


    @Bean
    public RouteLocator customerRouteLocato2r(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/trace-b/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(new RateLimitByIpGatewayFilter(10,1, Duration.ofSeconds(1)))
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://TRACE-B")
                        .order(0)
                        .id("trace_customer_service")
                )
                .build();
    }

    @Bean
    public RouteLocator customerRouteLocator3(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/zuul-server/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(new RateLimitByCpuGatewayFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://ZUUL-SERVER")
                        .order(0)
                        .id("trace_customer_service")
                )
                .build();
    }


}
