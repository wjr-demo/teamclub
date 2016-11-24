package controllers.backend

import commons.Eithers
import controllers.backend.IndexAction._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Action
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

  val loginForm = Form(
    tuple(
      "username" -> text,
      "password" -> text
    )
  )

  def loginInvoke = Action{ implicit request =>
    val (username, password) = loginForm.bindFromRequest.get
    val resp = loginService.loginInvoke(username, password)
    if(resp.left.isDefined){
      Ok(Eithers.success).withSession("connected" -> username)
    }else {
      Ok(Eithers.failure(resp.right.get))
    }
  }
}
