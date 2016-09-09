package baser.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import plugins.spring.Spring;
import plugins.spring.SpringPlugin;

import java.lang.reflect.Method;

/**
 * Created by zhangmeng on 16-7-14.
 */
@Component
@Aspect
public class AspectInterceptor{

    /***
     ** 切点 　　
     */
    @Pointcut("@annotation(baser.aspect.With)")

    public void interceptPoint() {};

    /***
     * 执行方法
     */
    @Around("interceptPoint()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        if(signature instanceof MethodSignature){
            MethodSignature msign = (MethodSignature) signature;
            Method method = msign.getMethod();
            With an = method.getAnnotation(With.class);
            Class<? extends Interceptor>[] clazz = an.value();
            Interceptor[] instance = new Interceptor[clazz.length];
            for(int i=0;i<clazz.length;i++){
                instance[i] = Spring.getBeanOfType(clazz[i]);
            }
            final InvocationContext context = new InvocationContext(joinPoint,this,instance);
            Object beforResult = invokerOnBefor(context, instance);
            if(beforResult != null) return beforResult;
            final Object result = invoke(context); //如果这里出现异常
            return invokerOnAfter(context, instance, result);
        }else{
            return joinPoint.proceed();
        }
    }

    public Object invoke(final InvocationContext context) throws Throwable{
        return context.getJoinPoint().proceed();
    }

    public Object invokerOnBefor(final InvocationContext context,final Interceptor[] instance) throws Throwable{
        Object result = null; //final result
        for(int i=0;i<instance.length;i++){
            result = instance[i].onBefor(context);
            if(result != null){
                return result; //请求被拦截
            }
        }
        return result;
    }

    public Object invokerOnAfter(final InvocationContext context,final Interceptor[] instance,Object result) throws Throwable{
        Object realResult = result,tmp = result;
        for(int i=0;i<instance.length;i++){
            tmp = instance[i].onAfter(context,realResult);
            if(tmp != null){
                realResult = tmp;
                break;
            }
        }
        return realResult;
    }
}