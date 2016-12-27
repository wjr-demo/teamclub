package plugin.dongkaiyue

import play.api.mvc._
import router.RoutePlugin

/**
 * Created by zhangmeng on 16-6-30.
 */
class DongkaiyuePlugin(app: play.api.Application) extends RoutePlugin {
  override val prefix = Some("dongkaiyue")
  override val prefixHandler: Option[RequestHeader => Option[Handler]] = Some( {
    rh: RequestHeader =>
      None
    }
  )
}


