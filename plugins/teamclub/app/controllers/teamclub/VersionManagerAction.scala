package controllers.teamclub

import commons.{ErrorCodes, Eithers}
import controllers.backend.Authenticated
import forms._
import models.xteamclub.VersionManager
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import plugins.ebean.Paging
import services.teamclub.VersionManagerService
import VersionManagerForm._
import forms._
/**
 * Created by zhangmeng on 16-9-8.
 */
object VersionManagerAction extends Controller{
  def list = Authenticated { implicit request =>
    versionManagerForm.bindFromRequest.fold(
      error => Ok(Eithers.failure(error.errorsAsJson.toString)),
      form => {
//        val versionManager: VersionManager = form
        val list = VersionManagerService.list(form)
        list.fold({
          list => Ok(Paging.toPage(list).toJson())
        }, {
          err => Ok(Eithers.failure(err))
        })
      }
    )
  }

  def modify = Authenticated { implicit request =>
    versionManagerForm.bindFromRequest.fold(
      error => Ok(Eithers.failure(error.errorsAsJson.toString)),
      form => {
        val versionManger: Option[VersionManager] = form
        versionManger match {
          case Some(v) => {
            if(form.id.isDefined) v.update else v.save
            Ok(Eithers.success)
          }
          case None => Ok(Eithers.failure(ErrorCodes.BUSIZ_FAILURE))
        }
      }
    )

  }


  def testForm = Action { implicit request =>
    versionManagerForm.bindFromRequest.fold(
      formWithError => {
        Ok(formWithError.errorsAsJson)
      },
      form => {
        Logger.info(form.currentPage.toString)
        Ok("SUCCESS")
      }
    )
  }
}
