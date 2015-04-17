<%@ page import="org.haibin369.jms.MessageSender" %>
<%@ page import="org.haibin369.jms.MessageReceiver" %>
<%@ page import="org.haibin369.jms.util.JMSUtils" %>
<%@ page import="javax.jms.*" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Date" %>
<html>
<body>
<h2>JMS</h2>
<%
//    new MessageSender().sendMessage();
//    Thread.sleep(2000);
//    TextMessage textMessage = (TextMessage) new MessageReceiver().receiveMessage();
//    out.println(textMessage);
//    out.println(textMessage.getText());

//    new MessageReceiver().receiveAsyncMessage();
//    new MessageSender().sendMessage();
//    Thread.sleep(1000);
//    new MessageSender().sendMessage();

    new MessageSender().sendMessage();
    new MessageSender().sendMessage();
//    new MessageReceiver().receiveMessage();
//    new MessageReceiver().receiveMessage();
//    new MessageReceiver().receiveMessage();
//    new MessageReceiver().receiveMessage();
//    new MessageReceiver().receiveMessage();
    ConnectionFactory connectionFactory = JMSUtils.lookupJNDI("ConnectionFactory");
    Connection connection = connectionFactory.createConnection();
    Session jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Queue queue = JMSUtils.lookupJNDI("MyQueue");
    QueueBrowser browser = jmsSession.createBrowser(queue);
    Enumeration enumeration = browser.getEnumeration();
    while(enumeration.hasMoreElements()){
        TextMessage textMessage = (TextMessage) enumeration.nextElement();
        System.out.println(textMessage);
        System.out.println(new Date(textMessage.getJMSTimestamp()));
        System.out.println(textMessage.getText());
    }
%>
</body>
</html>
