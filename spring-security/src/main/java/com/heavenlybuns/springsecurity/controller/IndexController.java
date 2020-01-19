package com.heavenlybuns.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: Shr
 * @description: TODO
 * @date 2020/1/19 14:46
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";

    }

}
