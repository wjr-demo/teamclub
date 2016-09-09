package baser.aspect;

/**
 * Created by zhangmeng on 16-7-14.
 */
public interface Interceptor{

    Object onBefor(InvocationContext context) throws Throwable;

    Object onAfter(InvocationContext context,Object result) throws Throwable;
}

