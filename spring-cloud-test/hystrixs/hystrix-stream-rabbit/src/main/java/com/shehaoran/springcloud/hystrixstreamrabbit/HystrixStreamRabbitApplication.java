package com.shehaoran.springcloud.hystrixstreamrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableTurbineStream
@SpringBootApplication
public class HystrixStreamRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixStreamRabbitApplication.class, args);
    }


   /* @Bean
    public ConfigurableCompositeMessageConverter resolverMessageConverter(CompositeMessageConverterFactory factory) {
        return new ConfigurableCompositeMessageConverter(factory.getMessageConverterForAllRegistered().getConverters());
    }*/

}
