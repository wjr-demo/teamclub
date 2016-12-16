package plugin.backend

import play.api.Logger
import play.api.mvc.Results._
import play.api.mvc._
import router.RoutePlugin

/**
 * Created by zhangmeng on 16-6-30.
 */
class BackendPlugin(app: play.api.Application) extends RoutePlugin {
  override val prefix = Some("backend")
  override val prefixHandler: Option[RequestHeader => Option[Handler]] = Some( {
    rh: RequestHeader =>
      None
    }
  )
}


