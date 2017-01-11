package controllers.base

import java.io.File

import play.api.mvc.{Action, AnyContent, Controller}

/**
 * Created by zhangmeng on 17-1-3.
 */
object ExterAssets extends Controller {

  val AbsolutePath = """^(|[a-zA-Z]:\\).*""".r

  def at(rootPath: String, file: String): Action[AnyContent] = Action { request =>
    val fileToServe = new File(rootPath, file)
    if (fileToServe.exists) {
      Ok.sendFile(fileToServe, fileName= _ => file).withHeaders(CACHE_CONTROL -> "max-age=3600").as("text/img")
    } else {
      NotFound
    }
  }
}
