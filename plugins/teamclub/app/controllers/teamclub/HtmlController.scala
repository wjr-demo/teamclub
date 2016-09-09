package controllers.teamclub

import play.api.mvc.{Action, Controller}
import plugins.freemarker.Freemarker.view

/**
 * Created by zhangmeng on 16-9-2.
 */
object HtmlController extends Controller {
  def index = Action {
    Ok(view("teamclub/htmltest.ftl"))
  }
}
