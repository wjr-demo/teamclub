package base;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import play.libs.Crypto;

/**
 * Created by zhangmeng on 16-11-4.
 */
public class Md5Test {
    @Test
    public void md5() {
        System.out.println(encryptPwd("123456"));
    }
    public String encryptPwd(String passwd) {
        return DigestUtils.md5Hex(DigestUtils.md5Hex("123456") + "xxx");
    }
}
