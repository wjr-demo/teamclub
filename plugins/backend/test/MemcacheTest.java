import freemarker.template.SimpleDate;
import models.AppDomain;
import models.AppFuncTree;
import org.junit.Test;
import play.cache.Cache;
import play.libs.Crypto;
import play.libs.Json;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by zhangmeng on 16-6-27.
 */
public class MemcacheTest extends BaseTest {
    @Test
    public void testMemcache(){
        String s = "a9A0X?XoB0nY`h;SNpO^qQ=p6Dqt:hv8[li_AcwI?wv4bnh7rGfEsnDZqHDad7Ybwjr";
        System.out.println(s.substring(0, 16));
        System.out.println(Crypto.encryptAES("admin123"));
    }

    @Test
    public void testEbean() {
        List<AppFuncTree> lists = AppFuncTree.finder().all();
        System.out.println(AppFuncTree.toJson(lists));
    }
}
