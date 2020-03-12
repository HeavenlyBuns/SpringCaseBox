package com.shehaoran.springcloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/3/11 17:51
 */
@RestController
public class FeignClientController {

    @Autowired
    private IndexFeign feign;

    @RequestMapping("/index")
    public String index() {
        return "feign访问结果: " + feign.index("feign");
    }


}
