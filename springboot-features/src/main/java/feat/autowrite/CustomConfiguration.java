package feat.autowrite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author user
 * @title: CustomConfiguration
 * @projectName my_git
 * @description: TODO
 * @date 2019/5/31 11:08
 */
@Configuration
public class CustomConfiguration {

    @Bean
    public BeanDemo reg(){
        return new BeanDemo();

    }

}