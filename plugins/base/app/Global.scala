import play.api.mvc.{Handler, RequestHeader}
import play.api.{Logger, Play, Application, GlobalSettings}
import plugins.spring.{SpringPlugin, Spring}

/**
 * Created by zhangmeng on 16-6-23.
 */
object Global extends GlobalSettings {

  private var springPlugin:SpringPlugin = _

  override def onStart(app: Application): Unit = {
    springPlugin = new SpringPlugin(app)
    Logger.info("start spring plugin.");
    springPlugin.onStart()
  }

  override def onStop(app: Application): Unit = {
    Logger.info("stop spring plugin.");
    springPlugin.onStop();
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Spring.getBeanOfType(controllerClass)
  }
  
}
