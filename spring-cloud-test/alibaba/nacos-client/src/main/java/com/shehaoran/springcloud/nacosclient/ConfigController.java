package com.shehaoran.springcloud.nacosclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/4/15 14:02
 */
@RefreshScope
@RequestMapping("/config")
@RestController
public class ConfigController {


    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get(){return useLocalCache;}

}
