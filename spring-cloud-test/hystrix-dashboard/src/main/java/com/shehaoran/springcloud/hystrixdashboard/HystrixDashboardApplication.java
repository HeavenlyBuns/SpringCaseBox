package com.shehaoran.springcloud.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 *启用 Hystrix Dashboard 功能。
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {


    /**
     * 1.Hystrix-Dashboard 的主界面上输入要监控的服务例如: hystrix
     *      监控的服务需要:  @EnableHystrix
     *                     添加配置:
     *                     management:
                            endpoints:
                              web:
                               exposure:
                                 include: hystrix.stream
     *
     * 2.该服务对应的地址 http://localhost:9004/actuator/hystrix.stream
     * 3.然后点击 Monitor Stream 按钮，进入页面。
     *
     * 如果没有请求会一直显示 “Loading…”，这时访问 http://localhost:9004/actuator/hystrix.stream 也是不断的显示 “ping”。
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

   /**
    * 默认的集群监控：通过 URL：http://turbine-hostname:port/turbine.stream 开启，实现对默认集群的监控。
    *  指定的集群监控：通过 URL：http://turbine-hostname:port/turbine.stream?cluster=[clusterName] 开启，实现对 clusterName 集群的监控。
    *   单体应用的监控： 实现对具体某个服务实例的监控。
    *（ 现在这里的 URL 应该为 http://hystrix-app:port/actuator/hystrix.stream，
    * Actuator 2.x 以后  endpoints 全部在 /actuator 下，可以通过
    * management.endpoints.web.base-path 修改）
    */

}
