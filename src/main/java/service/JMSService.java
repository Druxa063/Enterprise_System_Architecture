package service;



import dao.JPALogger;
import model.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless(name = "JMSService")
public class JMSService {

    @Resource(name= "jms/Education_ESA")
    private ConnectionFactory connectionFactory;

    @Resource(name="jms/topicESA")
    private Destination destination;

    @EJB(beanName = "JPALogger")
    private JPALogger jpaLogger;

    public void sendString(String text) {
        try {
            //создаем подключение
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            //добавим в JMS сообщение собственное свойство в поле сообщения со свойствами
            message.setStringProperty("clientType", "web clien");
            //добавляем payload в сообщение
            message.setText(text);
            //отправляем сообщение
            producer.send(message);
            System.out.println("message sent");
            //закрываем соединения
            session.close();
            connection.close();

            jpaLogger.create(new Logger(text));
        } catch (JMSException ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        }
    }
}
