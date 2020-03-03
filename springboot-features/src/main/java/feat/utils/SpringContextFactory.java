package feat.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author user
 * @title: SpringContextFactory
 * @projectName yanwei-cloud-package
 * @description: spring context 上下文获取
 * @date 2019/5/13 19:41
 */
public class SpringContextFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
