package aoptest.com.byteslounge.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by zhangmeng on 16-7-13.
 */
@Aspect
public class PointcutDefinition {

    @Pointcut("within(com.byteslounge.web..*)")
    public void webLayer() {
    }

    @Pointcut("within(com.byteslounge.service..*)")
    public void serviceLayer() {
    }

    @Pointcut("within(com.byteslounge.dao..*)")
    public void dataAccessLayer() {
    }

}