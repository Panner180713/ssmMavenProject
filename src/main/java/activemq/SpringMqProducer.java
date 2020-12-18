//package activemq;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Service;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
///**
// * @Author chenshoukai
// * @Date 2020/04/12 20:03
// */
//@Service
//public class SpringMqProducer {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public static void main(String[] args) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("main.config/applicationContext.xml");
//
//        SpringMqProducer springMqProducer = (SpringMqProducer) ctx.getBean("springMqProducer");
//
//        springMqProducer.jmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage();
//                textMessage.setText("---->>>spring-activemq-queue-message01<<<----");
//                return textMessage;
//            }
//        });
//
//        System.out.println("***发布消息到queue成功***");
//    }
//}
