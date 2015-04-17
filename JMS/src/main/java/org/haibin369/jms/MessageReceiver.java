package org.haibin369.jms;

import org.haibin369.jms.util.JMSUtils;

import javax.jms.*;
import javax.naming.NamingException;

public class MessageReceiver {
    private static final String CONNECTION_FACTORY_JNDI_NAME = "ConnectionFactory";
    private static final String MESSAGE_QUEUE_JNDI_NAME = "MyQueue";

    public Message receiveMessage() throws NamingException, JMSException {
        ConnectionFactory connectionFactory = JMSUtils.lookupJNDI(CONNECTION_FACTORY_JNDI_NAME);
        Destination destination = JMSUtils.lookupJNDI(MESSAGE_QUEUE_JNDI_NAME);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        connection.start();
        MessageConsumer messageConsumer = session.createConsumer(destination);
        TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);
        System.out.println(textMessage);
        System.out.println(textMessage.getText());

        session.close();
        connection.close();

        return textMessage;
    }

    public void receiveAsyncMessage() throws NamingException, JMSException, InterruptedException {
        ConnectionFactory connectionFactory = JMSUtils.lookupJNDI(CONNECTION_FACTORY_JNDI_NAME);
        Destination destination = JMSUtils.lookupJNDI(MESSAGE_QUEUE_JNDI_NAME);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        connection.start();
        MessageConsumer messageConsumer = session.createConsumer(destination, "ContentType = 'text'");
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage);
                    System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(5000);

        session.close();
        connection.close();
    }

}


