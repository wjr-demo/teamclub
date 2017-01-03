package controllers.backend

import play.api.mvc.{Action, Controller}
import plugins.freemarker.Freemarker._

/**
 * Created by zhangmeng on 17-1-3.
 */
object TestAction extends Controller {
  def test = Action {
    Ok(view("backend/test.ftl"))
  }
}
