package controllers.backend

import commons.{ErrorCodes, Eithers}
import commons.kaptcha.KaptchaService
import controllers.backend.IndexAction._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.Action
import plugins.freemarker.Freemarker._
import plugins.spring.Spring
import services.backend.ILogin
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Created by zhangmeng on 16-11-21.
 */
object LoginAction {
  val loginService: ILogin = Spring.getBean("backend.loginFactory").asInstanceOf[ILogin]

  def login = Action {
    val html = loginService.loginHtml
    Ok(view(html)).withNewSession
  }

  val loginForm = Form(
    tuple(
      "username" -> text,
      "password" -> text,
      "captcha" -> text,
      "token" -> text
    )
  )

  def loginInvoke = Action.async { implicit request =>
    val (username, password, captcha, token) = loginForm.bindFromRequest.get
    KaptchaService.retrieve(token).map {
      oCapText => {
        if(oCapText.exists(captcha == _.text)) {
          val resp = loginService.loginInvoke(username, password)
          if(resp.left.isDefined){
            Ok(Eithers.success).withSession("connected" -> resp.left.get)
          }else {
            Ok(Eithers.failure(resp.right.get))
          }
        }else {
          Ok(Eithers.failure(ErrorCodes.of("验证码错误")))
        }
      }
    }
  }

  def captcha = Action.async { implicit request =>
    KaptchaService.create().map {
      captcha =>
        Ok(Json.toJson(Map("token" -> captcha._1.uuid, "captcha" -> captcha._2)))
    }
  }
}
