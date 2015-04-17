package org.haibin369.jms;

import org.haibin369.jms.util.JMSUtils;

import javax.jms.*;
import javax.naming.NamingException;

public class MessageSender {
    private static final String CONNECTION_FACTORY_JNDI_NAME = "ConnectionFactory";
    private static final String MESSAGE_QUEUE_JNDI_NAME = "MyQueue";

    public void sendMessage() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = JMSUtils.lookupJNDI(CONNECTION_FACTORY_JNDI_NAME);
        Destination destination = JMSUtils.lookupJNDI(MESSAGE_QUEUE_JNDI_NAME);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        TextMessage textMessage = session.createTextMessage();
        textMessage.setStringProperty("ContentType", "text");
        textMessage.setText("This is a Messagegg.");

        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        messageProducer.setTimeToLive(3000);
        messageProducer.send(textMessage);

        session.close();
        connection.close();
    }

    public static void main(String[] args) throws JMSException, NamingException {
        new MessageSender().sendMessage();
    }

}


