package service.impl;

import service.TestShiroService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

/**
 * @Author chenshoukai
 * @Date 2020/06/08 17:41
 */
@Service
//设置bean的作用域
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
//@Lazy
public class TestShiroServiceImpl implements TestShiroService, InitializingBean, DisposableBean, ServletContextAware {

    private ServletContext servletContext;

//    @RequiresUser
    @Override
    public ModelAndView getModelAndViewByUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("---------->>>>>>>>1.TestShiroServiceImpl的postConstructMethod方法执行了");
    }

    @PreDestroy
    public void preDestroyMethod(){
        System.out.println("----------->>>>>>>2.TestShiroServiceImpl的preDestroyMethod方法执行了");
    }

    public void initMethod(){
        System.out.println("---------->>>>>>>>3.TestShiroServiceImpl的initMethod方法执行了");

        System.out.println("---------->>>>>>>>8."+ servletContext.getContextPath() +"");
    }

    public void destroyMethod(){
        System.out.println("---------->>>>>>>>4.TestShiroServiceImpl的destroyMethod方法执行了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------->>>>>>>>5.TestShiroServiceImpl的afterPropertiesSet方法执行了");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("---------->>>>>>>>6.TestShiroServiceImpl的destroy方法执行了");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        System.out.println("---------->>>>>>>>7.TestShiroServiceImpl的setServletContext方法执行了");
    }
}
