package controllers.backend

import java.io.File
import java.util

import libs.backend.Libs
import play.api.Play.current
import play.api.mvc.{Action, Controller}
import play.api.{Logger, Play}
import play.libs.Json
/**
 * Created by zhangmeng on 17-1-3.
 */
object FileUploadAction extends Controller {
  def upload = Action(parse.multipartFormData) { request =>
    val map = new util.HashMap[String, String]()
    try {
      request.body.files.foreach { v =>
        val suffix = if (v.filename.indexOf(".") != -1 ) v.filename.substring(v.filename.indexOf(".") + 1) else ""
        val _path = s"files/${Libs.genePicId}.${suffix}"
        val path = Play.application.path.getAbsolutePath + s"/public/${_path}"
        Logger.info(s"save path is ${path}")
        v.ref.moveTo(new File(path))
        map.put("path", _path)
      }
      Ok(Json.stringify(Json.toJson(map)))
    } catch {
      case e: Exception => {
        map.put("error", e.getMessage)
        Logger.error("", e)
        Ok(Json.stringify(Json.toJson(map)))
      }
    }
  }
}
