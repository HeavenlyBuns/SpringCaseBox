package com.shehaoran.springcloud.cloudgetway.hystrix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/9 16:47
 */
@RestController
public class HystrixFallbackController {

    @GetMapping("/fallback")
    public String fallback(){
        return "Hello world gateway";

    }

}
