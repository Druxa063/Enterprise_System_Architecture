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
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    @Override
    public void onMessage(javax.jms.Message msg) {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        TextMessage message = (TextMessage) msg;
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("party.manager0419@gmail.com", "PartyManager0419");
                    }
                });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("party.manager0419@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("andru-163@mail.ru"));
            mimeMessage.setSubject("Testing Subject");
            mimeMessage.setText(message.getText());

            Transport.send(mimeMessage);

            System.out.println("Done");

        } catch (MessagingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
