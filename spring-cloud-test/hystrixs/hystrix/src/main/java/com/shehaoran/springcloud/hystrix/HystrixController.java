package com.shehaoran.springcloud.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/12 14:45
 */
@RestController
public class HystrixController {

    @Autowired
    private IndexFeign indexFeign;

    @RequestMapping("/index")
    public String index(){

        return indexFeign.index("hystrix");
    }


}
