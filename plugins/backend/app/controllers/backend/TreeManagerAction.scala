package controllers.backend

import commons.Eithers
import forms.backend.Forms
import models.{AppFuncTree, AppSubjectUser}
import play.api.mvc.{Action, Controller}
import play.libs.Json
import plugin.backend.actions.Authenticated
import services.backend.TreeManagerService
import forms.backend.FormMappers._

/**
 * Created by zhangmeng on 16-11-28.
 */
object TreeManagerAction extends Controller{
  def list = Authenticated { implicit request =>
    val formMapper = treeManagerFormMapper.bindFromRequest()
    formMapper.fold (
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        if(form.appId.isDefined) {
          val user = new AppSubjectUser
          user.appId = form.appId.get
          user.isSysAdmin = true
          val resp = TreeManagerService.list(form, user)
          Ok(Eithers.toJson(resp))
        } else {
          val resp = TreeManagerService.list(form, request.sess.appSubjectUser)
          Ok(Eithers.toJson(resp))
        }
      }
    )
  }

  def findById = Authenticated { implicit request =>
    val formMapper = treeManagerFormMapper.bindFromRequest()
    Forms.required(formMapper, Array("id"))
    formMapper.fold (
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val appFunc = AppFuncTree.finder.byId(form.id.get)
        Ok(Json.toJson(appFunc).toString)
      }
    )
  }

  def add = Authenticated { implicit request =>
    val formMapper = treeManagerFormMapper.bindFromRequest()
    formMapper.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = TreeManagerService.add(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def del = Authenticated { implicit request =>
    val formMapper = treeManagerFormMapper.bindFromRequest()
    Forms.required(formMapper, Array("id"))
    formMapper.fold(
      error => {
        Ok(Eithers.failure(error))
      },
      form => {
        val resp = TreeManagerService.del(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }

  def parentlist = Action { implicit request =>
    val formMapper = treeManagerFormMapper.bindFromRequest()
    formMapper.fold(
      error => Ok(Eithers.failure(error)),
      form => {
        val resp = TreeManagerService.parentlist(form)
        Ok(Eithers.toJson(resp))
      }
    )
  }
}
