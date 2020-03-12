package com.shehaoran.springcloud.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/11 17:47
 */
@FeignClient(name = "eureka-producer",fallback = IndexFeignFallback.class)
public interface IndexFeign {

    @GetMapping(value = "/index")
    String index(@RequestParam(name = "name") String name);

}
