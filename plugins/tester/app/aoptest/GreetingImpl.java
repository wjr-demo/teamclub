package aoptest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zhangmeng on 16-7-13.
 */
@Component
public class GreetingImpl implements Greeting{
    @Override
    public void showHello() {
        System.out.println("Show HELLO");
    }
}
