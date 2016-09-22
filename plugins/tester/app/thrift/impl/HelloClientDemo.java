package thrift.impl;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.HelloWordService;
import thrift.Request;
import thrift.RequestType;

/**
 * Created by zhangmeng on 16-9-18.
 */
public class HelloClientDemo {
    public static void main(String[] args) throws Exception {
        startClient("Danial");
    }

    public static void startClient(String userName) throws Exception {
        TTransport transport = null;
        try{
            transport = new TSocket("localhost", 7912, 3000);
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWordService.Client client = new HelloWordService.Client(protocol);
            transport.open();
            Request request = new Request(RequestType.SAY_HELLO, userName);
            request.setAge(30);
            String result = client.doAction(request);
            System.out.println("Thrify client result =: " + result);
        } finally {
            if(null != transport) {
                transport.close();
            }
        }

    }
}
