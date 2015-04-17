package org.haibin369.webservice.test;

import org.haibin369.webservice.handler.HeaderHandler;
import org.haibin369.webservice.model.User;
import org.haibin369.webservice.util.Constants;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class SOAPTest {
    private static URL URL;

    @BeforeClass
    public static void beforeClass() throws MalformedURLException {
        URL = new URL(Constants.URL_PATH);
    }

    @Test
    public void testPostMessage() throws SOAPException, MalformedURLException {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        QName postMsgQName = new QName(Constants.NAME_SPACE, "postMessage", "ns");
        SOAPBodyElement soapBodyElement = soapBody.addBodyElement(postMsgQName);
        soapBodyElement.addChildElement("message").setTextContent("this is a test.");
        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);
        dispatch.invokeAsync(soapMessage);
    }

    @Test
    public void testGetMessage() throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        QName postMsgQName = new QName(Constants.NAME_SPACE, "getMessage", "ns");
        soapBody.addBodyElement(postMsgQName);
        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);

        SOAPMessage responseMsg = dispatch.invoke(soapMessage);
        responseMsg.writeTo(System.out);
    }

    @Test
    public void testAddUser() throws SOAPException, MalformedURLException, JAXBException {
        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);

        User user = new User("benchen", "fortest");
        JAXBContext jaxbCtx = JAXBContext.newInstance(User.class);
        Marshaller marshaller = jaxbCtx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(user, stringWriter);

        StringBuilder payloadBuilder = new StringBuilder(200);
        payloadBuilder.append("<ns:addUser xmlns:ns=\"").append(Constants.NAME_SPACE).append("\">")
                .append(stringWriter.toString())
                .append("</ns:addUser>");

        Dispatch<Source> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), Source.class, Service.Mode.PAYLOAD);
        dispatch.invoke(new StreamSource(new StringReader(payloadBuilder.toString())));
    }

    @Test
    public void testGetAllUsers() throws SOAPException, IOException, JAXBException {
        testAddUser();

        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        QName postMsgQName = new QName(Constants.NAME_SPACE, "getAllUsers", "ns");
        soapBody.addBodyElement(postMsgQName);
        QName headerQName = new QName(Constants.NAME_SPACE, "authInfo", "ns");
        soapMessage.getSOAPHeader().addChildElement(headerQName).setValue("BENCHEN");

        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);

        SOAPMessage responseMsg = dispatch.invoke(soapMessage);
        responseMsg.writeTo(System.out);
    }

    @Test
    public void testGetAllUsersFail() throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        QName postMsgQName = new QName(Constants.NAME_SPACE, "getAllUsers", "ns");
        soapBody.addBodyElement(postMsgQName);
        QName headerQName = new QName(Constants.NAME_SPACE, "authInfo", "ns");
        soapMessage.getSOAPHeader().addChildElement(headerQName).setValue("wrong");

        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);

        try {
            dispatch.invoke(soapMessage);
            fail("No exception thrown.");
        } catch (SOAPFaultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetAllUsersWithHandler() throws Exception {
        testAddUser();

        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPBody soapBody = soapMessage.getSOAPBody();
        QName postMsgQName = new QName(Constants.NAME_SPACE, "getAllUsers", "ns");
        soapBody.addBodyElement(postMsgQName);

        QName qName = new QName(Constants.NAME_SPACE, "MyService");
        Service service = Service.create(URL, qName);
        service.setHandlerResolver(new HandlerResolver() {
            public List<Handler> getHandlerChain(PortInfo arg0) {
                List<Handler> handlerList = new ArrayList<Handler>();
                handlerList.add(new HeaderHandler());
                return handlerList;
            }

        });
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(Constants.NAME_SPACE, "MyServicePort"), SOAPMessage.class, Service.Mode.MESSAGE);

        SOAPMessage responseMsg = dispatch.invoke(soapMessage);
        responseMsg.writeTo(System.out);
    }
}
