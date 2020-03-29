package com.shehaoran.springcloud.busserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BusServerApplication {
    /**
     * @link http://localhost:11800/config-git-dev.yml 可查看是否配置成功
     * management:
     *   endpoints:
     *     web:
     *       exposure:
     *         include: bus-refresh
     *
     * 暴露bus刷新
     *
     *  //localhost:11800/actuator/bus-refresh/
     */
    public static void main(String[] args) {
        SpringApplication.run(BusServerApplication.class, args);
    }

}
