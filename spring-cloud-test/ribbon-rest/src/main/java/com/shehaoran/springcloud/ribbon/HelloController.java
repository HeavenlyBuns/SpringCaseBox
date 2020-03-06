package com.shehaoran.springcloud.ribbon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/6 17:07
 */

@RestController
public class HelloController {

    @RequestMapping("/index")
    public String hello(){
        return "heelo";
    }

}
