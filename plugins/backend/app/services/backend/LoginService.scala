package services.backend

import com.avaje.ebean.Ebean
import commons.ErrorCode
import commons.ErrorCodes
import models.AppSubjectUser
import org.springframework.stereotype.Component
import play.api.Play
import play.api.libs.Crypto
import play.libs.F
import java.util.Map

/**
 * Created by zhangmeng on 16-9-7.
 */
@Component("backend.LoginService") class LoginService extends ILogin {
  import play.api.Play.current

  val appid = Play.application.configuration.getString("appid").getOrElse("backend")

  def loginHtml: String = {
    return "backend/login.ftl"
  }

  def loginInvoke(username: String, password: String): F.Either[String, ErrorCode] = {
    val appSubjectUser: AppSubjectUser = AppSubjectUser.finder.where.eq("username", username).eq("appId", appid).setMaxRows(1).findUnique
    if (appSubjectUser != null) {
      if(appSubjectUser.password != null && appSubjectUser.password == Crypto.encryptAES(password)) {
        val resp = Crypto.encryptAES("HELLO" + appSubjectUser.getId)
        return F.Either.Left(resp)
      }else {
        return F.Either.Right(ErrorCodes.of("账户和密码不对应"))
      }
    }
    else {
      return F.Either.Right(ErrorCodes.of("没有查询到用户"))
    }
  }
}