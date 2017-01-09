package controllers.backend

import java.io.File

import play.api.Play
import play.api.mvc.{Action, AnyContent, Controller}

/**
 * Created by zhangmeng on 17-1-3.
 */
object ExterAssets extends Controller {
  import play.api.Play.current

  val AbsolutePath = """^(|[a-zA-Z]:\\).*""".r

  def at(rootPath: String, file: String): Action[AnyContent] = Action { request =>
      val fileToServe = rootPath match {
        case AbsolutePath(_) => new File(rootPath, file)
        case _ => new File(Play.application.getFile(rootPath), file)
      }

      if (fileToServe.exists) {
        Ok.sendFile(fileToServe, fileName= _ => file).withHeaders(CACHE_CONTROL -> "max-age=3600")
      } else {
        NotFound
      }

    }
}
