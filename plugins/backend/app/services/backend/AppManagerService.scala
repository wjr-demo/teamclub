package services.backend

import java.util.Date

import com.avaje.ebean.ExpressionList
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.AppManagerForm
import models.AppDomain
import play.libs.Json
import plugin.backend.actions.XSession
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
      }
      case None => {
        model.save
      }
    }
    Left(ErrorCodes.SUCCESS)
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
