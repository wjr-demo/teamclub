package baser.aspect;

import com.google.common.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;

/**
 * Created by zhangmeng on 16-7-14.
 */
public class InvocationContext {
    public final Map<Object, Object> args = Maps.newHashMapWithExpectedSize(6);
    private ProceedingJoinPoint joinPoint;
    private AspectInterceptor interceptor;
    private Interceptor[] intercepters;

    public InvocationContext(ProceedingJoinPoint joinPoint, AspectInterceptor interceptor, Interceptor[] intercepters) {
        super();
        this.joinPoint = joinPoint;
        this.interceptor = interceptor;
        this.intercepters = intercepters;
    }

    public ProceedingJoinPoint getJoinPoint() {
        return joinPoint;
    }

    public AspectInterceptor getInterceptor() {
        return interceptor;
    }

    public Interceptor[] getIntercepters() {
        return intercepters;
    }

    public Object recover() throws Throwable {
        Object result = this.interceptor.invoke(this);
        return this.interceptor.invokerOnAfter(this, intercepters, result);
    }
}
