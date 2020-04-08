package com.shehaoran.springcloud.cloudgetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
