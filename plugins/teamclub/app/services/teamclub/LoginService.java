package services.teamclub;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import commons.Eithers;
import commons.ErrorCode;
import commons.ErrorCodes;
import models.teamclub.AppUser;
import org.springframework.stereotype.Component;
import play.api.libs.json.JsValue;
import play.libs.F;
import play.libs.Json;
import services.backend.ILogin;

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
        AppUser unique = Ebean.getServer("jira").find(AppUser.class).where().eq("userKey", username).findUnique();
        if(unique != null) {
            return F.Either.Left(username);
        }else {
            return F.Either.Right(ErrorCodes.of("没有查询到用户"));
        }
    }
}
