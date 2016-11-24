import freemarker.template.SimpleDate;
import models.AppDomain;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(AppDomain.finder().byId(9).getUpdatedAt()));
        System.out.println(Json.toJson(AppDomain.finder().byId(9)));
        System.out.println(new java.util.Date());
    }
}
