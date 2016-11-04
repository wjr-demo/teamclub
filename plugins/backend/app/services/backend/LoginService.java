package services.backend;

import com.avaje.ebean.Ebean;
import commons.ErrorCode;
import commons.ErrorCodes;
import models.AppSubjectUser;
import models.teamclub.AppUser;
import org.springframework.stereotype.Component;
import play.libs.F;

import java.util.Map;

/**
 * Created by zhangmeng on 16-9-7.
 */
@Component("teamclub.LoginService")
public class LoginService implements ILogin{
    @Override
    public String loginHtml() {
        return "teamclub/login.ftl";
    }

    @Override
    public F.Either<String, ErrorCode> loginInvoke(Map<String, String> req) {
        String username = req.get("username");
        AppSubjectUser appSubjectUser = Ebean.find(AppSubjectUser.class).where().eq("username", username).findUnique();
        if(unique != null) {
            return F.Either.Left(username);
        }else {
            return F.Either.Right(ErrorCodes.of("没有查询到用户"));
        }
    }
}
