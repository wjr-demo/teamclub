package controllers.backend

import commons.Eithers
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.UserDepartChangeService
import forms.backend.FormMappers._

/**
 * Created by zhangmeng on 16-12-29.
 */
object UserDepartChangeAction extends Controller {
  def list = Authenticated { implicit request =>
    userDepartChangeMapper.bindFromRequest().fold(
      error => Ok(Eithers.failure(error)),
      form => {
        UserDepartChangeService.list
        Ok
      }
    )
  }
  def add = Authenticated { implicit request =>
    userDepartChangeMapper.bindFromRequest().fold(
      error => Ok(Eithers.failure(error)),
      form => {
        UserDepartChangeService.add
        Ok
      }
    )
  }
  def del = Authenticated { implicit request =>
    userDepartChangeMapper.bindFromRequest().fold(
      error => Ok(Eithers.failure(error)),
      form => {
        UserDepartChangeService.del
        Ok
      }
    )
  }
}
