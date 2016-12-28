package services.backend

import com.avaje.ebean.ExpressionList
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.AdminAreaCodeForm
import models.AdminAreaCode
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-28.
 */
object AdminAreaCodeService {
  def list(form: AdminAreaCodeForm): Either[JsonNode, ErrorCode] =  {
    val expr = AdminAreaCode.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def add(form: AdminAreaCodeForm): Either[ErrorCode, ErrorCode] = {
    val model = form.toModel()
    form.id match {
      case Some(v) => {
        model.update
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        model.save
        Left(ErrorCodes.SUCCESS)
      }
    }
  }

  def del(form: AdminAreaCodeForm): Either[ErrorCode, ErrorCode] = {
    form.id match {
      case Some(v) => {
        AdminAreaCode.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => Right(ErrorCodes.of("id not set"))
    }
  }

  def expression(expr: ExpressionList[AdminAreaCode], form: AdminAreaCodeForm): ExpressionList[AdminAreaCode] = {
    form.id map (expr.eq("id", _))
    form.areaCode map (expr.eq("areaCode", _))
    form.areaName map (expr.eq("areaName", _))
    form.status map (expr.eq("status", _))
    form.parentCode map ( expr.eq("parentCode", _))
    expr.orderBy("areaCode asc")
    expr
  }
}
