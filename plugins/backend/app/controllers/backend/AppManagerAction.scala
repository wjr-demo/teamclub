package controllers.backend

import java.util.Date

import commons.Eithers
import forms.backend.Forms._
import forms.backend.FormMappers._
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.AppManagerService

/**
 * Created by zhangmeng on 16-11-21.
 */
object AppManagerAction extends Controller{
  def list = Authenticated { implicit request =>
    appManagerFormMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      appManagerForm => {
        val resp = AppManagerService.list(appManagerForm)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def add = Authenticated { implicit request =>
    val form = appManagerFormMapper.bindFromRequest
    form.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      appManagerForm => {
        appManagerForm.updatedAt = Some(new Date().getTime)
        appManagerForm.updatedBy = Some(request.sess.username)
        val resp = AppManagerService.add(appManagerForm)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def del = Authenticated { implicit request =>
    val form = appManagerFormMapper.bindFromRequest
    val reqForm = required(form, Array("id"))
    reqForm.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      appManagerForm => {
        val resp = AppManagerService.del(appManagerForm)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
