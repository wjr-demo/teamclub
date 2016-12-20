package services.backend

import com.avaje.ebean.ExpressionList
import commons.{ErrorCode, ErrorCodes}
import forms.backend.RoleManagerForm
import models.AppRole
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-19.
 */
object RoleManagerService {
  def list(form: RoleManagerForm) = {
    val expr = AppRole.finder.where()
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def add(form: RoleManagerForm): Either[ErrorCode, ErrorCode] = {
    val appRole = form.toModel()
    form.id.fold(appRole.save())(v => appRole.update())
    Left(ErrorCodes.SUCCESS)
  }

  def del(form: RoleManagerForm): Either[ErrorCode, ErrorCode] = {
    form.id match {
      case Some(v) => {
        AppRole.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.BUSIZ_FAILURE)
      }
    }
  }

  def expression(expr: ExpressionList[AppRole], form: RoleManagerForm): ExpressionList[AppRole] = {
    form.id.map(expr.eq("id", _))
    form.rolename.map(expr.eq("roleName", _))
    form.organNo.map(expr.eq("organNo", _))
    expr
  }
}
