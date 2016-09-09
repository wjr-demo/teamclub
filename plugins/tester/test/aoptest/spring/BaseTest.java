package aoptest.spring;

import aoptest.GreetingImpl;
import aoptest.com.byteslounge.service.ExampleService;
import aoptest.com.byteslounge.service.TestAction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;
import plugins.spring.Spring;
import services.IcashService;

/**
 * Created by zhangmeng on 16-6-27.
 */
public class BaseTest {
    private static FakeApplication fakeApplication = null;
    @BeforeClass
    public static void before(){
        fakeApplication = Helpers.fakeApplication();
        Helpers.start(fakeApplication);
    }
    @Test
    public void testAspect(){
//        ExampleService beanOfType = Spring.getBeanOfType(ExampleService.class);
//        beanOfType.showHello();
        IcashService bean = (IcashService) Spring.getBean("cashsyncservice.common");
        bean.show();
    }

    @Test
    public void start(){
        System.out.println("Start..");
    }

    @AfterClass
    public static void after(){
        if(fakeApplication != null) Helpers.stop(fakeApplication);
    }
}
