package feat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
@RestController
@SpringBootApplication
public class Application {

    @Value("${name:123}")
    Integer name;

    @Autowired
    DataSource dataSource;


    @PostConstruct
    public void initize() {
        System.out.println(" name =  " + name);

    }


    @RequestMapping(value = "/index2")
    public String index(){
        System.out.println();
//        int exit = SpringApplication.exit(run);
//        System.out.println("退出码为： " + exit );
//        System.exit(exit);
        return "index";
    }

    public static void main(String[] args) throws InterruptedException {
//        addSystemShutdownHook();
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
    }


    public static void addSystemShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("JVM 挂钩启动..............................");

        }));

    }

}
