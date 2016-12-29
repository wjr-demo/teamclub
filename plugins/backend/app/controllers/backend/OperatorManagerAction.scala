package controllers.backend

import commons.{Libs, Eithers}
import models.AppSubjectUser
import play.api.cache.Cache
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.OperatorManagerService
import forms.backend.FormMappers._
import play.api.Play.current

/**
 * Created by zhangmeng on 16-12-19.
 */
object OperatorManagerAction extends Controller{
  def list = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        form.appId = Some(request.sess.appid)
        val resp = OperatorManagerService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def add = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        form.appId = Some(request.sess.appid)
        val resp = OperatorManagerService.add(form)
        form.id map { v => Cache.remove(Libs.CachePrefix.LOGIN + v) }
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def del = Authenticated { implicit request =>
    appSubjectUserMapper.bindFromRequest.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = OperatorManagerService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
