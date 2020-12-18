package service;

import org.springframework.web.servlet.ModelAndView;

/**
 * @Author chenshoukai
 * @Date 2020/06/08 17:40
 */
public interface TestShiroService {

    /**
     * 测试service层使用注解
     * @return
     */
    ModelAndView getModelAndViewByUser();


}
