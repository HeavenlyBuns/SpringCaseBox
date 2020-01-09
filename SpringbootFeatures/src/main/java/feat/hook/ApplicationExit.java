package feat.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationExit {
    private Logger logger = LoggerFactory.getLogger(ApplicationExit.class);
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        logger.info("[应用退出] -------------- exit ------------------");
        return () -> 0; //自定义退出码
    }

}
