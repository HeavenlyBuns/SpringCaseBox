package com.shehaoran.springcloud.hystrixturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class HystrixTurbineApplication {
    /**
     * 访问 Hystrix Dashboard 并开启对 localhost:11500/turbine.stream  的监控，
     * 1.启动两个 hystrix服务,那么访问中的 host2将为2
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication.class, args);
    }

}
