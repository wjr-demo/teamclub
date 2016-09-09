import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.test.FakeApplication;
import play.test.Helpers;

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

    @AfterClass
    public static void after(){
        if(fakeApplication != null) Helpers.stop(fakeApplication);
    }
}
