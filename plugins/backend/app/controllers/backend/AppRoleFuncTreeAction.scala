package controllers.backend

import commons.Eithers
import forms.backend.FormMappers._
import forms.backend.Forms
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.AppRoleFuncTreeService

/**
 * Created by zhangmeng on 16-12-20.
 */
object AppRoleFuncTreeAction extends Controller{

  def treeRoleList = Authenticated { implicit request =>
    val formMapper = appRoleFuncTreeMapper.bindFromRequest
    val requiredForm = Forms.required(formMapper, Array("roleId", "appId"))
    requiredForm.fold(
      error => Ok(Eithers.failure(error)),
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AppRoleFuncTreeService.treeRoleList(form.roleId.getOrElse(0), request.sess.appSubjectUser)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def list = Authenticated { implicit request =>
    appRoleFuncTreeMapper.bindFromRequest.fold(
      error => Ok(Eithers.failure(error)),
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AppRoleFuncTreeService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def add = Authenticated { implicit request =>
    val formMapper = appRoleFuncTreeMapper.bindFromRequest
    Forms.required(formMapper, Array("roleId"))
    formMapper.fold(
      error => Ok(Eithers.failure(error)),
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AppRoleFuncTreeService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )

  }
  def del = ???
}
