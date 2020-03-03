package feat.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @title: IndexController
 * @projectName my_git
 * @description: demo
 * @date 2019/4/29 14:48
 */


@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String indexException(){

        int a = 1/0;
        return "";
    }


}
