import org.junit.Test;
import play.cache.Cache;
/**
 * Created by zhangmeng on 16-6-27.
 */
public class MemcacheTest extends BaseTest {
    @Test
    public void testMemcache(){
        Cache.set("key", "hahahahha");
        System.out.println(Cache.get("key"));
    }
}
