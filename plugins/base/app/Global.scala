import play.api.mvc.Results._
import play.api.mvc.{SimpleResult, Handler, RequestHeader}
import play.api._
import plugins.spring.{SpringPlugin, Spring}

import scala.concurrent.Future

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
    Logger.info("stop spring plugin.")
    if(springPlugin != null) springPlugin.onStop()
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Spring.getBeanOfType(controllerClass)
  }

  override def onHandlerNotFound(request: RequestHeader): Future[SimpleResult] = {
    Logger.info(s"request method: ${request.path}")
    if("/" == request.path) {
      Future.successful(Redirect("/login/login"))
    }else {
      Future.successful(NotFound(Play.maybeApplication.map {
        case app if app.mode != Mode.Prod => views.html.defaultpages.devNotFound.f
        case app => views.html.defaultpages.notFound.f
      }.getOrElse(views.html.defaultpages.devNotFound.f)(request, Play.maybeApplication.flatMap(_.routes))))
    }
  }
  
}
