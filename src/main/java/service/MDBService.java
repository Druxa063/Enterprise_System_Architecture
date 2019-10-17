package service;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@MessageDriven(
        //имя topic, на который подписан бин
        mappedName = "jms/topicESA",
        name = "MDBService")
public class MDBService implements MessageListener {

    private Properties props;

    public MDBService() {
        this.props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.user", "party.manager0419@gmail.com");
    }

    @Override
    public void onMessage(javax.jms.Message msg) {
        TextMessage message = (TextMessage) msg;

        try {
            Session session = Session.getDefaultInstance(props);
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("party.manager0419@gmail.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("andru-163@mail.ru"));
            mimeMessage.setSubject("Testing Subject");
            mimeMessage.setText(message.getText());

            Transport transport = session.getTransport();
            transport.connect(null, "");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

            System.out.println("Done");

        } catch (MessagingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
