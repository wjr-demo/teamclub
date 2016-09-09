package baser.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import play.Logger;

import java.util.Arrays;

/**
 * Created by zhangmeng on 16-7-14.
 */
@Component
public class LogTimeInterceptor implements Interceptor {
    @Override
    public Object onBefor(InvocationContext context) throws Throwable {
        Logger.info("Log before invoke..");
        return null;
    }

    @Override
    public Object onAfter(InvocationContext context, Object result) throws Throwable {
        Logger.info("Log after invoke..");
        return result;
    }
}
