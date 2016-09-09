package controllers.test

import play.api.mvc.{Action, Controller}

/**
 * Created by zhangmeng on 16-8-9.
 */
object XmlController extends Controller{
  def index = Action {
    Ok("it's works")
  }
}
