package com.shehaoran.springcloud.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulProxy
@SpringBootApplication
public class ZuulServerApplication {

    /**
     * 一般路由方式:  zullServer ip:port/访问的路由服务名称/路由的接口名
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
