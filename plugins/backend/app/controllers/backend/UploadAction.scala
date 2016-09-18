package controllers.backend

import play.api.mvc.Controller

/**
 * Created by zhangmeng on 16-9-12.
 */
object UploadAction extends Controller {
  def upload = Authenticated(parse.multipartFormData) { implicit request =>
    request.body.file("uploadFile").map { file =>
      Ok("{}")
    }. getOrElse {
      Ok("{}")
    }
  }
}
