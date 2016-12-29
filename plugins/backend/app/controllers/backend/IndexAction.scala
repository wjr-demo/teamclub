package controllers.backend

import com.avaje.ebean.Ebean
import models.{AppDomain, AppFuncTree, AppRoleFuncTree}
import play.api.mvc.Controller
import play.libs.{Json, Scala}
import plugin.backend.actions.Authenticated
import plugins.freemarker.Freemarker._

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  def index = Authenticated { implicit request =>
    val appDomain = AppDomain.finder.where().eq("appid", request.sess.appid).setMaxRows(1).findUnique()
    val title = if(appDomain != null) appDomain.getAppname else "管理平台"
    Ok(view("backend/index.ftl",new Arg("title", title), new Arg("SC_CURRENT", {})));
  }
}
