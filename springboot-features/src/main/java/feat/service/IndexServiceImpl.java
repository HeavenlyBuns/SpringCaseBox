package feat.service;

import org.springframework.stereotype.Service;

/**
 * @author user
 * @title: IndexServiceImpl
 * @projectName my_git
 * @description: TODO
 * @date 2019/5/31 11:11
 */
@Service
public class IndexServiceImpl implements IndexService{
    @Override
    public void index() {
        System.out.println("开始");

    }
}
