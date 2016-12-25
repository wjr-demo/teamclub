package services.backend

import com.avaje.ebean.{Ebean, ExpressionList}
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCode, ErrorCodes}
import forms.backend.AppManagerForm
import libs.backend.Libs
import models.{AppDomain, AppSubjectUser}
import org.apache.commons.codec.digest.DigestUtils
import play.api.Logger
import play.api.libs.Crypto
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-11-22.
 */
object AppManagerService {
  def list(form: AppManagerForm): Either[JsonNode, ErrorCode] = {
    val expr = AppDomain.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def add(form: AppManagerForm): Either[ErrorCode,ErrorCode] = {
    val model = form.toModel()
    form.id match {
      case Some(v) => {
        model.update
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        val trans =  Ebean.beginTransaction();
        try {
          model.save
          val subjectUser = new AppSubjectUser
          val organNo = Libs.geneOrganNum()
          subjectUser.setUsername(organNo + "@" + organNo)
          subjectUser.appId = model.appid
          subjectUser.deptid = 0
          subjectUser.isSysAdmin = true
          subjectUser.password = Crypto.encryptAES(DigestUtils.md5Hex("admin123"))
          subjectUser.save()
          trans.commit()
          Left(ErrorCodes.SUCCESS)
        } catch{
          case e: Exception => {
            Logger.error("", e)
            Right(ErrorCodes.of(e.getMessage))
          }
        } finally {
          trans.end()
        }
      }
    }
  }

  def del(form: AppManagerForm): Either[ErrorCode,ErrorCode] = {
    form.id match {
      case Some(v) => {
        AppDomain.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.BUSIZ_FAILURE)
      }
    }
  }

  def expression(expr: ExpressionList[AppDomain], form: AppManagerForm): ExpressionList[AppDomain] = {
    form.id.map { v => expr.eq("id", v)}
    form.appid.map {v => expr.like("appid", v + "%")}
    form.appname map { v => expr.like("appname", v + "%")}
    expr
  }
}
