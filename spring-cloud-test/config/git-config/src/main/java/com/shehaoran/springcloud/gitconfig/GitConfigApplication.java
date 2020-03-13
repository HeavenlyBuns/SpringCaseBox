package com.shehaoran.springcloud.gitconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class GitConfigApplication {


    /**
     * 1. http://localhost:11600/git-config/dev 可确认连接的git是否有问题
     * 2. http://localhost:11600/config-git-dev.yml 可查看git的config下的文件
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(GitConfigApplication.class, args);
    }

}
