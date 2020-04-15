package com.shehaoran.springcloud.eureka;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/6 17:07
 */

@RestController
public class HelloController {

    @RequestMapping("/index")
    public String hello(@RequestParam(defaultValue = "") String name) {

        return "这里是eureka客户端提供的index服务!欢迎 " + name + " 访问";
    }

    @RequestMapping("/json")
    public String bodyData(@RequestBody Map<String, Object> map) {
        System.out.println("接收到json字符串,大小为: " + map.size());
        return map.toString();
    }

}
