package services.backend;

import commons.ErrorCode;
import play.libs.F;

import java.util.Map;

/**
 * Created by zhangmeng on 16-9-7.
 */
public interface ILogin {
    String loginHtml();
    F.Either<String, ErrorCode> loginInvoke(Map<String, String> req);
}
