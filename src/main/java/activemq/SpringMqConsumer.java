package activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author chenshoukai
 * @Date 2020/04/12 20:20
 */
@Service
public class SpringMqConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMqConsumer springMqConsumer = (SpringMqConsumer) ctx.getBean("springMqConsumer");

        String result = (String) springMqConsumer.jmsTemplate.receiveAndConvert();

        System.out.println(result);

        System.out.println("***consumer接收消息成功***");
    }
}
