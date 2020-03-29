package com.shehaoran.springcloud.gitconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitConfigClientApplication {

    /**
     * 实现在线刷新配置需要添加 spring-boot-starter-actuator
     * 它是一套监控的功能,可以监控程序在运行时状态，其中就包括 /actuator/refresh 的功能。
     * 1.将 /actuator/refresh 这个 Endpoint 暴露出来(配置文件添加配置)
     * 2. POST访问  localhost:11700/actuator/refresh,即可在线刷新配置
     */
    public static void main(String[] args) {
        SpringApplication.run(GitConfigClientApplication.class, args);
    }

}
