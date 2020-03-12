package com.shehaoran.springcloud.hystrix;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/12 14:46
 */
@Component
public class IndexFeignFallback implements IndexFeign{

    @Override
    public String index(String name) {
        System.out.println("熔断器打开,服务异常!");
        return "熔断器打开..";
    }
}
