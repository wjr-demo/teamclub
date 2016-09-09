package plugin.teamclub

import play.api.Logger
import play.api.mvc.RequestHeader
import router.RoutePlugin

/**
 * Created by zhangmeng on 16-6-30.
 */
class TeamClubPlugin(app: play.api.Application) extends RoutePlugin {

  override val prefix = Some("teamclub")

}
