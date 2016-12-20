package controllers.backend

import java.util.Date

import commons.Eithers
import forms.backend.FormMappers
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.RoleManagerService
import FormMappers._

/**
 * Created by zhangmeng on 16-12-19.
 */
object RoleManagerAction extends Controller {
  def list = Authenticated { implicit request =>
    roleManagerFormMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = RoleManagerService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def add = Authenticated { implicit request =>
    roleManagerFormMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        form.createdAt = Some(new Date().getTime)
        form.createdBy = Some(request.sess.username)
        form.appid = Some(request.sess.appid)
        form.organNo = Some(request.sess.organNo)
        val resp = RoleManagerService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def del = Authenticated { implicit request =>
    roleManagerFormMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = RoleManagerService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
