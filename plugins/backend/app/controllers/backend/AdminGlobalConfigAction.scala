package controllers.backend

import commons.Eithers
import forms.backend.{Forms, FormMappers, AdminGlobalConfigForm}
import play.api.mvc.{Action, Controller}
import play.libs.Json
import plugin.backend.actions.Authenticated
import services.backend.AdminGlobalConfigService

/**
 * Created by zhangmeng on 16-12-26.
 */
object AdminGlobalConfigAction extends Controller {

  def add = Authenticated { implicit request =>
    val mapper = FormMappers.adminGlobalConfigMapper.bindFromRequest()
    mapper.fold (
      error => {
        Ok(Eithers.failure(mapper))
      },
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AdminGlobalConfigService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def list = Authenticated { implicit request =>
    val mapper = FormMappers.adminGlobalConfigMapper.bindFromRequest()
    mapper.fold (
      error => {
        Ok(Eithers.failure(mapper))
      },
      form => {
        form.appId = Some(request.sess.appid)
        val resp = AdminGlobalConfigService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def jsList = Authenticated { implicit request =>
    val dict = Json.stringify(AdminGlobalConfigService.jsList)
    val scCurrent = Json.toJson(request.sess.appSubjectUser)
    val str =
      s"""
        |define(function(){
        | window.globalCfgDict = new Func.DictUtils(${dict});
        | var SC = window.SC = window.SC || {}; SC.current=${scCurrent}
        |})
        |
      """.stripMargin
    Ok(str).as("text/javascript; charset=utf8")
  }

  def del = Authenticated { implicit request =>
    val mapper = FormMappers.adminGlobalConfigMapper.bindFromRequest()
    Forms.required(mapper, Array("id"))
    mapper.fold (
      error => {
        Ok(Eithers.failure(mapper))
      },
      form => {
        val resp = AdminGlobalConfigService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
