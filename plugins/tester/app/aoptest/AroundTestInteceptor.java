package aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by zhangmeng on 16-7-13.
 */
@Component
@Aspect
public class AroundTestInteceptor {
    public AroundTestInteceptor() {}

    @Pointcut(value = "@annotation(aoptest.AroundTest)")
    public void logAnnotatedMethod(){

    }

    @Around("logAnnotatedMethod()")
    public Object inteceptorAction(ProceedingJoinPoint pjp) throws Throwable {
        Object o = null;
        MethodSignature joinPointObject = (MethodSignature)pjp.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(AroundTest.class);
        if(flag) {
            AroundTest annotation = method.getAnnotation(AroundTest.class);
            Date enterDate = new Date();
            System.out.println("开始执行方法 : " + annotation.name());
            o = pjp.proceed();
            Date leaveDate = new Date();
            System.out.println("结束执行方法 : " + annotation.name() + ", 方法执行的时间： " +
                    (leaveDate.getTime() - enterDate.getTime()));
        }
        return o;
    }
}
