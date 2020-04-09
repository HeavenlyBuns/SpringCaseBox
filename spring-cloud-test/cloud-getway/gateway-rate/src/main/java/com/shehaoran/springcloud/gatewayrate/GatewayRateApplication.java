package com.shehaoran.springcloud.gatewayrate;

import com.shehaoran.springcloud.gatewayrate.rate.RemoteAddrKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRateApplication.class, args);
    }

    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }

}
