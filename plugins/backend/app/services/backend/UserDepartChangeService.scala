package services.backend

import com.avaje.ebean.ExpressionList
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.UserDepartChangeForm
import models.UserDepartChange
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-29.
 */
object UserDepartChangeService {
  def list(form: UserDepartChangeForm): Either[JsonNode, ErrorCode] = {
    val expr = UserDepartChange.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def del(form: UserDepartChangeForm): Either[ErrorCode, ErrorCode] = {
    form.id match {
      case Some(v) => {
        UserDepartChange.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.ID_NOT_FOUND)
      }
    }
  }

  def add(form: UserDepartChangeForm): Either[ErrorCode, ErrorCode] = {
    val model = form.toModel()
    form.id.fold(model.save)(model.update)
    Left(ErrorCodes.SUCCESS)
  }

  def expression(expr: ExpressionList[UserDepartChange], form: UserDepartChangeForm): Unit = {
    form.id.map(expr.eq("id", _))
    form.changeDate.map(expr.eq("changeDate", _))
    form.userId.map(expr.eq("userId", _))
    form.departId.map(expr.eq("departId", _))
    form.roleId.map(expr.eq("roleId", _))
  }
}
