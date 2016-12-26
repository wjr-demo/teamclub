import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhangmeng on 16-12-23.
 */
public class TTest {
    @Test
    public void BaseTest(){
        Assert.assertEquals(Base64.class.getPackage().getSpecificationVersion(), "1.6");
        System.out.println(new Base64().encodeToString("hello world".getBytes()));
    }
}
