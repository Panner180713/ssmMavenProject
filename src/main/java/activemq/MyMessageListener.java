//package activemq;
//
//import org.springframework.stereotype.Component;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
///**
// * @Author chenshoukai
// * @Date 2020/04/12 20:35
// */
//@Component
//public class MyMessageListener implements MessageListener {
//
//    public void onMessage(Message message) {
//        if(message instanceof TextMessage){
//            TextMessage textMessage = (TextMessage) message;
//            try {
//                System.out.println(textMessage.getText());
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
