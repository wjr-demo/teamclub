package aoptest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangmeng on 16-7-13.
 */
public class CGLibDynamicProxy implements MethodInterceptor {
    public static CGLibDynamicProxy instance = new CGLibDynamicProxy();
    private CGLibDynamicProxy() {}
    public static CGLibDynamicProxy getInstance() { return instance; }


    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }

    private void before() {
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }
}
