package org.haibin369.webservice.service;

import javax.xml.ws.Endpoint;

public class WebservicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/MyWebService", new MyService());
    }
}
