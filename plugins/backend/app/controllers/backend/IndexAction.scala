package controllers.backend

import com.avaje.ebean.Ebean
import commons.Eithers
import models.AppFuncTree
import play.api.Play
import play.api.mvc.{Action, Controller}
import play.core.j.JavaParsers
import play.libs.Scala
import plugins.freemarker.Freemarker._
import plugins.spring.Spring
import services.backend.ILogin

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  val appid = Play.current.configuration.getString("appid").getOrElse("")
  val loginSer = Play.current.configuration.getString("loginService")

  val loginService = Spring.getBean(loginSer.getOrElse("")).asInstanceOf[ILogin]

  def login = Action {
    val html = loginService.loginHtml()
    Ok(view(html)).withNewSession
  }

  def loginInvoke = Action(parse.json){ request =>
    val json = request.body
    val username = (json \ "username").as[String]
    val map = Map("username" -> username)
    val resp = loginService.loginInvoke(Scala.asJava(map))
    if(resp.left.isDefined){
      Ok(Eithers.success).withSession("connected" -> username)
    }else {
      Ok(Eithers.failure(resp.right.get))
    }
  }

  def index = Authenticated {
    val appFuncTrees = Ebean.find(classOf[AppFuncTree]).where().eq("appId", appid).findList
    Ok(view("backend/index.ftl",new Arg("title", "PLATFORM"), new Arg("trees", appFuncTrees)));
  }
}
