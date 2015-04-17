
package org.haibin369.webservice.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.haibin369.webservice.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PostMessage_QNAME = new QName("http://service.webservice.haibin369.org/", "postMessage");
    private final static QName _GetMessageResponse_QNAME = new QName("http://service.webservice.haibin369.org/", "getMessageResponse");
    private final static QName _GetMessage_QNAME = new QName("http://service.webservice.haibin369.org/", "getMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.haibin369.webservice.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.haibin369.webservice.generated.GetMessage }
     * 
     */
    public GetMessage createGetMessage() {
        return new GetMessage();
    }

    /**
     * Create an instance of {@link PostMessage }
     * 
     */
    public PostMessage createPostMessage() {
        return new PostMessage();
    }

    /**
     * Create an instance of {@link org.haibin369.webservice.generated.GetMessageResponse }
     * 
     */
    public GetMessageResponse createGetMessageResponse() {
        return new GetMessageResponse();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link PostMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.haibin369.org/", name = "postMessage")
    public JAXBElement<PostMessage> createPostMessage(PostMessage value) {
        return new JAXBElement<PostMessage>(_PostMessage_QNAME, PostMessage.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.haibin369.webservice.generated.GetMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.haibin369.org/", name = "getMessageResponse")
    public JAXBElement<GetMessageResponse> createGetMessageResponse(GetMessageResponse value) {
        return new JAXBElement<GetMessageResponse>(_GetMessageResponse_QNAME, GetMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.haibin369.webservice.generated.GetMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.haibin369.org/", name = "getMessage")
    public JAXBElement<GetMessage> createGetMessage(GetMessage value) {
        return new JAXBElement<GetMessage>(_GetMessage_QNAME, GetMessage.class, null, value);
    }

}
