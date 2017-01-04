package controllers.backend

import java.util.Date

import commons.{Libs, Eithers}
import play.api.cache.Cache
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
        val resp = UserDepartChangeService.list(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def add = Authenticated { implicit request =>
    userDepartChangeMapper.bindFromRequest().fold(
      error => Ok(Eithers.failure(error)),
      form => {
        form.changeDate = Some(new Date().getTime())
        val resp = UserDepartChangeService.add(form, request.sess)
        import play.api.Play.current
        form.userId map { v => Cache.remove(Libs.CachePrefix.LOGIN + v) }
        Ok(Eithers.toJson(resp))
      }
    )
  }
  def del = Authenticated { implicit request =>
    userDepartChangeMapper.bindFromRequest().fold(
      error => Ok(Eithers.failure(error)),
      form => {
        val resp = UserDepartChangeService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
