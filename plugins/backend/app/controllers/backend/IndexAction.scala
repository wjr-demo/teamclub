package controllers.backend

import com.avaje.ebean.Ebean
import models.{AppRoleFuncTree, AppFuncTree}
import play.api.mvc.Controller
import play.libs.Scala
import plugin.backend.actions.Authenticated
import plugins.freemarker.Freemarker._

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  def index = Authenticated { implicit request =>
    Ok(view("backend/index.ftl",new Arg("title", "平台")));
  }
}
