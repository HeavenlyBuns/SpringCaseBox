package com.shehaoran.springcloud.busclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/13 15:33
 */

/**
 * @RefreshScope 开启在线更新机制
 */
@RefreshScope
@RestController
public class IndexController {

    @Value("${neo.hello}")
    private String dev;

    @GetMapping("/index")
    public Mono<String> index(){
        return Mono.justOrEmpty(dev);
    }

}
