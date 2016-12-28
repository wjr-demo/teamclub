package controllers.backend

import commons.Eithers
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import forms.backend.FormMappers.adminAreaCodeMapper
import services.backend.AdminAreaCodeService

/**
 * Created by zhangmeng on 16-12-28.
 */
object AdminAreaCodeAction extends Controller{

  def list = Authenticated { implicit request =>
    adminAreaCodeMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = AdminAreaCodeService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def add = Authenticated { implicit request =>
    adminAreaCodeMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = AdminAreaCodeService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def del = Authenticated { implicit request =>
    adminAreaCodeMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = AdminAreaCodeService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

}
