package aoptest;

import aoptest.spring.GreetinBeforeAfterAdvice;
import aoptest.spring.GreetingAfterAdvice;
import aoptest.spring.GreetingAroundAdvice;
import aoptest.spring.GreetingBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by zhangmeng on 16-7-13.
 */
public class Client {
    public static void main(String[] args){
//        Gretting simple = new GrettingProxy(new GrettingImpl());
//        simple.sayHello("Jack");
//        Gretting jdkdynamic = new JDKDynamicProxy(new GrettingImpl()).getProxy();
//        jdkdynamic.sayHello("Jack");
//        Gretting cgdynamic = CGLibDynamicProxy.getInstance().getProxy(GrettingImpl.class);
//        cgdynamic.sayHello("Jack");

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GrettingImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        Gretting greeting = (Gretting)proxyFactory.getProxy();
        greeting.sayHello("jack");

    }
}
