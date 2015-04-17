package org.haibin369.webservice.generated;

public class WebserviceClient {
    public static void main(String[] args) {
        MyService wsService = new MyService();
        IMyService myService = wsService.getPort(IMyService.class);
//        String receivedMsg = myService.getMessage();
//        System.out.println(receivedMsg);

        myService.postMessage("test");
    }
}
