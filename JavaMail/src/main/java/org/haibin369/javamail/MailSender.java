package org.haibin369.javamail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailSender {
    public static void sendMail(final Map<String, String> params) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", getParam("smtpHost", params));
        properties.put("mail.smtp.port", getParam("smtpPort", params));
        properties.put("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getParam("username", params), getParam("password", params));
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(getParam("sourceAddress", params)));
        InternetAddress[] receiverAddresses = {new InternetAddress(getParam("receiverAddress", params))};
        message.setRecipients(Message.RecipientType.TO, receiverAddresses);
        message.setSubject("A sample mail");
        Multipart multipart = new MimeMultipart();
        MimeBodyPart content = new MimeBodyPart();
        content.setText("This is a mail from haibin369!");
        multipart.addBodyPart(content);

        FileDataSource dataSource = new FileDataSource("JavaMail/resources/test.txt");
        BodyPart attachment = new MimeBodyPart();
        attachment.setDataHandler(new DataHandler(dataSource));
        attachment.setFileName("sampleAttachment");
        multipart.addBodyPart(attachment);

        message.setContent(multipart);
        message.setSentDate(new Date());
        Transport.send(message);

    }

    private static String getParam(String key, Map<String, String> params) {
        String value = params.get(key);
        if (value == null) {
            throw new RuntimeException("Parameter not found: " + key);
        }
        return value;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        Map<String, String> params = new HashMap<String, String>(5);
        params.put("username", "haibin369@163.com");
        params.put("password", new String(new byte[]{104,97,105,98,105,110,64,50,53,53,53,57,55,55,48}, "UTF-8"));
        params.put("smtpHost", "smtp.163.com");
        params.put("smtpPort", "25");
        params.put("receiverAddress", "benchen@ecvision.com");
        params.put("sourceAddress", "haibin369@163.com");

        try {
            MailSender.sendMail(params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
