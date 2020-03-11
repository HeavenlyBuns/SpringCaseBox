package com.shehaoran.springcloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/6 17:07
 */

@RestController
public class HelloController {
    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用balancer得去掉 @LoadBalanced 注解
     * @return
     */
    @RequestMapping("/index-balancer")
    public String hello() {
        ServiceInstance instance = client.choose("eureka-producer");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/index";
        return "使用LoadBalancerClient和Rest访问: " + restTemplate.getForObject(url, String.class);
    }


    @RequestMapping("/index-ribbon")
    public String hello2() {
        String url = "http://eureka-producer" + "/index";
        return "使用Ribbon访问: " + restTemplate.getForObject(url, String.class);
    }

}
