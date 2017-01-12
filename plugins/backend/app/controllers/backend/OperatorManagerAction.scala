package controllers.backend

import commons.{Eithers, Libs}
import forms.backend.FormMappers._
import models.AppSubjectUser
import org.apache.commons.lang3.StringUtils
import play.api.Play.current
import play.api.cache.Cache
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.OperatorManagerService

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
        form.organNo = Some(request.sess.organNo)
        if(form.username.isDefined) {
          val username = form.username.get
          if(StringUtils.isNotBlank(username)) {
            if(AppSubjectUser.finder.where().like("username", username + "@%").findRowCount() > 0) {
              Ok(Eithers.failure("用户名已存在"))
            }
          }
        }
        val resp = OperatorManagerService.add(form, request.sess)
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

  def calcquitrate = Authenticated { implicit request =>
    val resp = OperatorManagerService.calcquitrate(request.sess)
    Ok(Eithers.toJson(resp))
  }
}
