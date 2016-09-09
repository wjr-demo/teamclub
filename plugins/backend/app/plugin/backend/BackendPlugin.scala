package plugin.backend

import play.api.Logger
import play.api.mvc.RequestHeader
import play.core.Router
import router.{RoutePlugin}

/**
 * Created by zhangmeng on 16-6-30.
 */
class BackendPlugin(app: play.api.Application) extends RoutePlugin {
  override val prefix = Some("backend")
  override val prefixHandler = {
    val resp  = (request: RequestHeader) => {
      request.session.get("connected") match {
        case Some(s) =>
        case None =>
      }
    }
    Some(resp)
  }
}
