package feat.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 *  CommandLineRunner、ApplicationRunner 接口是在容器启动成功后的最后一步回调（类似开机自启动）。
 *  执行时机为容器启动完成的时候。
 */
@Component
public class CustomCommandRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CustomCommandRunner.class);

    @Override
    public void run(String... args) throws Exception {
        print();
    }


    private void print(){
        System.out.println("************************************");
        System.out.println("************* 容器启动成功后的最后一步回调开始 ***********************");
        System.out.println("************  自定义动作执行 *********");
        System.out.println("************************************");

    }

}
