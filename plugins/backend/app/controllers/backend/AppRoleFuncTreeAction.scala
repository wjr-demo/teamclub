package controllers.backend

import forms.backend.FormMappers._
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated

/**
 * Created by zhangmeng on 16-12-20.
 */
object AppRoleFuncTreeAction extends Controller{
  def list = Authenticated { implicit request =>
//    appRoleFuncTreeMapper.bindFromRequest.fold(
    Ok
  }
  def add = ???
  def del = ???
}
