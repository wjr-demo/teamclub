package controllers.backend

import commons.Eithers
import models.AppSubjectUser
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.OperatorManagerService
import forms.backend.FormMappers._

/**
 * Created by zhangmeng on 16-12-19.
 */
object OperatorManagerAction extends Controller{
  def list = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => {
        Ok
      },
      form => {
        val resp = OperatorManagerService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def add = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => Ok,
      form => {
        val resp = OperatorManagerService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def del = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => Ok,
      form => {
        val resp = OperatorManagerService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
