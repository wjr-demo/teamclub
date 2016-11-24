package controllers.backend

import com.avaje.ebean.Ebean
import models.AppFuncTree
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import plugins.freemarker.Freemarker._

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  def index = Authenticated { request =>
    val appFuncTrees = Ebean.find(classOf[AppFuncTree]).where().eq("appId", request.sess.appid).findList
    Ok(view("backend/index.ftl",new Arg("title", "PLATFORM"), new Arg("trees", appFuncTrees)));
  }
}
