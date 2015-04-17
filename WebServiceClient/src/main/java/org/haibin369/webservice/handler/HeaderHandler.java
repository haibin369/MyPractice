package org.haibin369.webservice.handler;

import org.haibin369.webservice.util.Constants;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext ctx) {

        Boolean isOutBound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isOutBound) {
            try {
                SOAPMessage soapMessage = ctx.getMessage();
                SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = envelope.getHeader();

                if (soapHeader == null) {
                    soapHeader = envelope.addHeader();
                }
                QName headerQName = new QName(Constants.NAME_SPACE, "authInfo", "ns");
                soapHeader.addChildElement(headerQName).setValue("haibin");
                soapMessage.saveChanges();
                return true;

//                QName qname_user = new QName(Constants.NAME_SPACE, "AuthorServerImplService");
//
//                SOAPHeaderElement headerElement = soapHeader.addHeaderElement(qname_user);
//                headerElement.setActor(SOAPConstants.URI_SOAP_1_2_ROLE_NEXT);
//                headerElement.addTextNode("admin&admin1");
//                soapMessage.saveChanges();
//                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("Fault!");
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
