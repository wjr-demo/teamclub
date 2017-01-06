package controllers.backend

import commons.{Eithers, Libs}
import models.AppSubjectUser
import play.api.cache.Cache
import play.api.mvc.Controller
import plugin.backend.actions.Authenticated
import services.backend.OperatorManagerService
import forms.backend.FormMappers._
import play.api.Logger
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
        form.organNo = Some(request.sess.organNo)
        form.username match {
          case Some(v) if(v.trim() != "" && v.indexOf("@") == -1) =>  {
            Logger.warn(s"username is ${v}, 不符合规则")
            Ok((Eithers.failure("username 规则错误")))
          };
          case _ => {
            val resp = OperatorManagerService.add(form, request.sess)
            form.id map { v => Cache.remove(Libs.CachePrefix.LOGIN + v) }
            Ok(Eithers.toJson(resp))
          }
        }
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
