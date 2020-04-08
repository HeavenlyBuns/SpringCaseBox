package com.shehaoran.springcloud.zipkintrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/8 13:49
 */
@RestController
public class IndexController {

    @Autowired
    private LoadBalancerExchangeFilterFunction lbFunction;


    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("http://trace-b")
                .filter(lbFunction)
                .build();
    }

    @RequestMapping("/trace-a")
    public Mono<String> trace() {
        System.out.println("===call trace-a===");

        return webClient().get()
                .uri("/index")
                .retrieve()
                .bodyToMono(String.class);
    }

}
