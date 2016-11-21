package controllers.backend

import commons.Eithers
import controllers.backend.IndexAction._
import play.api.mvc.Action
import play.api.mvc.BodyParsers.parse
import play.libs.Scala
import plugins.freemarker.Freemarker._
import plugins.spring.Spring
import services.backend.ILogin

/**
 * Created by zhangmeng on 16-11-21.
 */
object LoginAction {
  val loginService: ILogin = Spring.getBean("backend.loginFactory").asInstanceOf[ILogin]

  def login = Action {
    val html = loginService.loginHtml
    Ok(view(html)).withNewSession
  }

  def loginInvoke = Action(parse.json){ request =>
    val json = request.body
    val username = (json \ "username").as[String]
    val password = (json \ "password").as[String]
    val resp = loginService.loginInvoke(username, password)
    if(resp.left.isDefined){
      Ok(Eithers.success).withSession("connected" -> username)
    }else {
      Ok(Eithers.failure(resp.right.get))
    }
  }
}
