package plugin.minishop

import play.api.Logger
import router.RoutePlugin

/**
 * Created by zhangmeng on 16-6-30.
 */
class MiniShopPlugin(app: play.api.Application) extends RoutePlugin {
  override val prefix = Some("minishop")
  Logger.info("mini shop plugin")
}
