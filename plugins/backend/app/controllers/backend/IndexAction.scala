package controllers.backend

import com.avaje.ebean.Ebean
import models.{AppFuncTree, AppRoleFuncTree}
import play.api.mvc.Controller
import play.libs.{Json, Scala}
import plugin.backend.actions.Authenticated
import plugins.freemarker.Freemarker._

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  def index = Authenticated { implicit request =>
    val json = Json.toJson(request.sess.appSubjectUser)
    Ok(view("backend/index.ftl",new Arg("title", "平台"), new Arg("SC_CURRENT", json)));
  }
}
