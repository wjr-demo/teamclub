package thrift.impl;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import thrift.HelloWordService;

import java.net.ServerSocket;

/**
 * Created by zhangmeng on 16-9-18.
 */
public class HelloWorldServer {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(7912);
        TProcessor tprocessor = new HelloWordService.Processor<HelloWordService.Iface>(new HelloServiceImpl());
        TServerSocket serverTransport = new TServerSocket(socket);
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());
        TServer server = new TSimpleServer(tArgs);
        server.serve();
    }
}
