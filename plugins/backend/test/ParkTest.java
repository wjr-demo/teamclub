import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import play.libs.Json;

import java.util.List;

/**
 * Created by zhangmeng on 16-6-28.
 */
public class ParkTest {
    public static void main(String[] args) throws Exception {
        List lists = Lists.newArrayList(
                ImmutableMap.of("id", "trade.weixin", "name", "微信"),
                ImmutableMap.of("id", "trade.alipay", "name", "支付宝"),
                ImmutableMap.of("id", "trade.qqpay", "name", "手Q支付"));
        System.out.println(Json.toJson(lists));
    }
}
