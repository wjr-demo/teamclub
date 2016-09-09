package aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import play.Logger;

/**
 * Created by zhangmeng on 16-7-13.
 */
//@Aspect
@Component
public class LogIntercept {

    @Pointcut(value = "execution(public * aoptest.com.byteslounge..*.*(..))")
    public void writeLog(){

    }

    @Before("writeLog()")
    public void before(){
        this.printLog("@Before 方法执行前－－－－做日志");
    }

    @After("writeLog()")
    public void after(){
        this.printLog("@After 方法执行后------做日志");
    }

    @Around("writeLog()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        this.printLog("@Around 方法执行前------做日志");
        pjp.proceed();
        this.printLog("@Around 方法执行后------做日志");
    }

    @AfterThrowing("writeLog()")
    public void afterThrow(){
        this.printLog("@AfterThrow 方法执行后----做日志");
    }


    private void printLog(String str){
        Logger.info(str);
    }

}
