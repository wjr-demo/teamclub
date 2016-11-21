package controllers.backend

import com.avaje.ebean.Ebean
import commons.Eithers
import models.AppFuncTree
import play.api.Play
import play.api.mvc.{Action, Controller}
import play.core.j.JavaParsers
import play.libs.Scala
import plugin.backend.actions.Authenticated
import plugins.freemarker.Freemarker._
import plugins.spring.Spring
import services.backend.ILogin

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  def index = Authenticated { request =>
    val appFuncTrees = Ebean.find(classOf[AppFuncTree]).where().eq("appId", request.sess.appid).findList
    Ok(view("backend/index.ftl",new Arg("title", "PLATFORM"), new Arg("trees", appFuncTrees)));
  }
}
