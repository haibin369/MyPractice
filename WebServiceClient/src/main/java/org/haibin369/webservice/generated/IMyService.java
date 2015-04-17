
package org.haibin369.webservice.generated;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "IMyService", targetNamespace = "http://service.webservice.haibin369.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IMyService {


    /**
     * 
     * @param message
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "postMessage", targetNamespace = "http://service.webservice.haibin369.org/", className = "org.haibin369.webservice.generated.PostMessage")
    public void postMessage(
            @WebParam(name = "message", targetNamespace = "")
            String message);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "responseMessage", targetNamespace = "")
    @RequestWrapper(localName = "getMessage", targetNamespace = "http://service.webservice.haibin369.org/", className = "org.haibin369.webservice.generated.GetMessage")
    @ResponseWrapper(localName = "getMessageResponse", targetNamespace = "http://service.webservice.haibin369.org/", className = "org.haibin369.webservice.generated.GetMessageResponse")
    public String getMessage();

}