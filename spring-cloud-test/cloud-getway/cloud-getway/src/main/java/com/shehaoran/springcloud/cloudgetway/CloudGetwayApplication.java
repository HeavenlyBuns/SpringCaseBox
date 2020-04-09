package com.shehaoran.springcloud.cloudgetway;

import com.shehaoran.springcloud.cloudgetway.filter.CustomGatewayFactory;
import com.shehaoran.springcloud.cloudgetway.rate.request_rate.RemoteAddrKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class CloudGetwayApplication {
    // Spring Cloud Gateway 自动的为我们的 consumer 创建了一个路由，类似于下边这样

    /**
     * Spring Cloud Gateway 自动的为我们的 consumer 创建了一个路由，类似于下边这样
     * routes:
     *   - id: CompositeDiscoveryClient_CONSUMER
     *     uri: lb://CONSUMER
     *     order: 0
     *     predicates:
     *       - Path=/CONSUMER/**
     *     filters:
     *       - RewritePath=/CONSUMER/(?<segment>.*), /$\{segment}
     */
    public static void main(String[] args) {
        SpringApplication.run(CloudGetwayApplication.class, args);
    }

    @Bean
    public CustomGatewayFactory customGatewayFactory(){
        return new CustomGatewayFactory();
    }


    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }

}
