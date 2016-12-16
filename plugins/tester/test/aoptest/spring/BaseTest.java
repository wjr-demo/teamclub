package aoptest.spring;

import aoptest.Print;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import plugins.spring.Spring;

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
        Print print = Spring.getBeanOfType(Print.class);
        print.print();
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
