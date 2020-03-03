package feat.autowrite;

import feat.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author user
 * @title: BeanDemo
 * @projectName my_git
 * @description: TODO
 * @date 2019/5/31 11:19
 */
public class BeanDemo {

    @Autowired
    private IndexService index;

    public IndexService getIndex() {
        return index;

    }


}
