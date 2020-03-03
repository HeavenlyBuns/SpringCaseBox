package base.starter;


import base.starter.common.DbProperties;
import base.starter.service.HelloService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableConfigurationProperties(DbProperties.class)
public class HelloAutoConfiguation {

    @Bean
    public HelloService getHello(DbProperties dbProperties){
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        hashMap.put("|","");
        return new HelloService(dbProperties);

    }


}
