package thrift.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import thrift.HelloWordService;
import thrift.Request;
import thrift.RequestException;
import thrift.RequestType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangmeng on 16-9-18.
 */
public class HelloServiceImpl implements HelloWordService.Iface{
    @Override
    public String doAction(Request request) throws org.apache.thrift.TException {
        System.out.println("Get request: " + request);
        if(StringUtils.isBlank(request.getName()) || request.getType() == null) {
            throw new RequestException();
        }

        String result = "Hello," + request.getName();

        if(request.getType() == RequestType.SAY_HELLO) {
            result += ", Welcome!";
        } else {
            result += ", Now is " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        }
        return result ;
    }
}
