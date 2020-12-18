package lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 1.Spring Bean的后置处理器，允许在调用初始化方法(init-method、destroy-method)前后对bean进行额外的处理
 * 2.bean后置处理器对ioc容器中的所有bean进行逐一处理，而非单一实例。
 * 典型应用：检查bean属性是否设置正确或根据特定的标准更改bean的属性。
 * 3.对bean的后置处理器而言，要实现BeanPostProcessor接口，
 * 在初始化方法被调用前后，spring将把每个bean实例分别传递给给上述接口的以下两个方法
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在bean的初始化方法之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization方法执行了");
        return bean;
    }

    /**
     * 在bean的初始化方法之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization方法执行了");
        return bean;
    }
}
