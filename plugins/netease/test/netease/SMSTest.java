package netease;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import play.libs.Crypto;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-9-2.
 */
public class SMSTest {
    String sendCodeUrl = "https://api.netease.im/sms/sendcode.action";
    String verifyCodeUrl = "https://api.netease.im/sms/verifycode.action";

    String sendTemplateUrl = "https://api.netease.im/sms/sendtemplate.action";
    String verifyTemplateUrl = "https://api.netease.im/sms/querystatus.action";

    public WS.WSRequestHolder normalRequestHolder(String url) {
        WS.WSRequestHolder wsRequestHolder = WS.url(url)
                .setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8;");
        for(Map.Entry<String, String> entry: getParams().entrySet()) {
            wsRequestHolder.setHeader(entry.getKey(), entry.getValue());
        }
        return wsRequestHolder;
    }

    public void invokeByParams(WS.WSRequestHolder wsRequestHolder, String str) {
        F.Promise<String> promise = wsRequestHolder.post(str).map(new F.Function<WS.Response, String>() {
            @Override
            public String apply(WS.Response response) throws Throwable {
                System.out.println(response.asJson());
                return null;
            }
        });
        promise.get(10, TimeUnit.SECONDS);
    }

    /***
     * appKey 和　appSecret　要替换
     * @return
     */
    public Map<String, String> getParams()  {
        String AppKey = "8b78616edef3deff4dc9ef44320801ac";
        String AppSecret = "fa81fdefd6c1";
        String Nonce = String.valueOf(System.currentTimeMillis());
        String CurTime = String.valueOf(System.currentTimeMillis() / 1000);
        String CheckSum = CheckSumBuilder.getCheckSum(AppSecret, Nonce, CurTime);
        Map<String, String> map = ImmutableMap.of("AppKey", AppKey, "CurTime", CurTime, "CheckSum", CheckSum, "Nonce", Nonce);
        return map;
    }

    /***
     * 测试发送短信验证码接口
     * @throws Exception
     */
    @Test
    public void testSendCode() throws Exception{
        WS.WSRequestHolder wsRequestHolder = normalRequestHolder(sendCodeUrl);
        invokeByParams(wsRequestHolder, "mobile=18575531620");
    }

    /***
     * 测试验证验证码接口
     */
    @Test
    public void testVerifyCode() {
        WS.WSRequestHolder wsRequestHolder = normalRequestHolder(verifyCodeUrl);
        invokeByParams(wsRequestHolder, "mobile=15919492724&code=1171");
    }

    @Test
    public void testSendTemplate() {

    }

}
