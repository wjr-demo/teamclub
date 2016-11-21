package plugin.backend

import play.api.mvc.{Handler, RequestHeader}
import router.RoutePlugin

/**
 * Created by zhangmeng on 16-11-8.
 */
class LoginPlugin (app: play.api.Application) extends RoutePlugin {
  override val prefix = Some("login")
  override val prefixHandler: Option[RequestHeader => Option[Handler]] = None
}
