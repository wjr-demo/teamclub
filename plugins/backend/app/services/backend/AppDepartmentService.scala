package services.backend

import com.avaje.ebean.ExpressionList
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCode, ErrorCodes}
import forms.backend.AppDepartmentForm
import models.AppDepartment
import plugins.ebean.Paging

/**
  * Created by Administrator on 2016/12/26.
  */
object AppDepartmentService {
  def list(form: AppDepartmentForm):Either[JsonNode, ErrorCode] = {
    val expr = AppDepartment.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def add(form: AppDepartmentForm): Either[ErrorCode, ErrorCode] = {
    val model = form.toModel()
    form.id match {
      case Some(v) => model.update
      case None => model.save
    }
    Left(ErrorCodes.SUCCESS)
  }

  def del(form: AppDepartmentForm): Either[ErrorCode, ErrorCode] = {
    form.id match {
      case Some(v) => {
        AppDepartment.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.of("id is not found"))
      }
    }
  }

  def expression(expr: ExpressionList[AppDepartment], form:AppDepartmentForm)  = {
    form.id.map(expr.eq("id", _))
    form.appId.map(expr.eq("appId", _))
    form.departName.map(expr.eq("departName", _))
    expr
  }
}
