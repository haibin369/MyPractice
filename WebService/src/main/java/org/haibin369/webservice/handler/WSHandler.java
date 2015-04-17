package org.haibin369.webservice.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.Iterator;
import java.util.Set;

public class WSHandler implements SOAPHandler<SOAPMessageContext> {
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isOutbound) {
            SOAPMessage soapMessage = context.getMessage();
            try {
                SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPBody soapBody = soapEnvelope.getBody();
                QName qName = new QName("http://service.webservice.haibin369.org/", "getAllUsers", "ns");
                if (!soapBody.getChildElements(qName).hasNext()) {
                    return true;
                }

                SOAPHeader soapHeader = soapEnvelope.getHeader();
                if (soapHeader == null) {
                    generateSoapFault(soapMessage, "No Message Header");
                }

                QName headerQName = new QName("http://service.webservice.haibin369.org/", "authInfo", "ns");
                Iterator it = soapHeader.getChildElements(headerQName);
                if (it == null || !it.hasNext()) {
                    generateSoapFault(soapMessage, "No authentication info");
                }

                Node node = (Node) it.next();
                String authInfo = node.getTextContent();
                if (!"admin".equals(authInfo)) {
                    System.out.println("Authentication fail: " + authInfo);
                    generateSoapFault(soapMessage, "Access Denied");
                }
            } catch (SOAPException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    private void generateSoapFault(SOAPMessage soapMessage, String reason) {
        try {
            SOAPBody soapBody = soapMessage.getSOAPBody();
            SOAPFault soapFault = soapBody.getFault();
            if (soapFault == null) {
                soapFault = soapBody.addFault();
            }
            soapFault.setFaultString(reason);

            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
