package services.backend

import com.avaje.ebean.ExpressionList
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.AdminGlobalConfigForm
import models.AdminGlobalConfig
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-26.
 */
object AdminGlobalConfigService {

  def add(form: AdminGlobalConfigForm): Either[ErrorCode, ErrorCode] = {
    val model = form.toModel()
    form.id match {
      case Some(v) => {
        model.update()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        model.save()
        Left(ErrorCodes.SUCCESS)
      }
    }
  }

  def list(form: AdminGlobalConfigForm): Either[JsonNode, ErrorCode] = {
    val expr = AdminGlobalConfig.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def del(form: AdminGlobalConfigForm): Either[ErrorCode, ErrorCode] = {
    form.id match {
      case Some(v) => {
        AdminGlobalConfig.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.of("id is empty !!"))
      }
    }
  }

  def expression(expr: ExpressionList[AdminGlobalConfig],form: AdminGlobalConfigForm) = {
    form.appId.map(expr.eq("appId", _))
    form.cfgName.map(expr.eq("cfgName", _))
    form.keyCode.map(expr.eq("keyCode", _))
    expr
  }

}
