import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import play.libs.Json;

import java.util.List;

/**
 * Created by zhangmeng on 16-6-28.
 */
public class ParkTest {
    public static void main(String[] args) throws Exception {
        System.out.println(new String(new Base64().encode(new byte[]{'A', 'B'})));
    }
}
