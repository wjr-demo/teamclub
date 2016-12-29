package controllers.backend

import commons.Eithers
import enums.backend.Department
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import forms.backend.FormMappers._
import services.backend.AppDepartmentService

/**
  * Created by Administrator on 2016/12/26.
  */
object AppDepartmentAction extends Controller{
  def list = Authenticated { implicit request =>
    val mapper = appDepartmentMapper.bindFromRequest()
    mapper.fold(
      error => Ok(Eithers.failure(mapper)),
      form => {
        val resp = AppDepartmentService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )

  }
  def add = Authenticated { implicit request =>
    val mapper = appDepartmentMapper.bindFromRequest()
    mapper.fold(
      error => Ok(Eithers.failure(mapper)),
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AppDepartmentService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def del  = Authenticated { implicit request =>
    val  mapper = appDepartmentMapper.bindFromRequest()
    mapper.fold(
      error => Ok(Eithers.failure(mapper)),
      form => {
        val resp = AppDepartmentService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def keycode = Authenticated { implicit request =>
    Ok(Eithers.toJson(Left(Department.getAll())))
  }
}
