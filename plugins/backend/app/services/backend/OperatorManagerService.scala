package services.backend

import com.avaje.ebean.ExpressionList
import commons.{ErrorCode, ErrorCodes}
import forms.backend.AppSubjectUserForm
import models.AppSubjectUser
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-19.
 */
object OperatorManagerService {
  def list(form: AppSubjectUserForm) =  {
    val expr = AppSubjectUser.finder.where();
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  def add(form: AppSubjectUserForm): Either[ErrorCode, ErrorCode] = {
    val appSubjectUser = form.toModel()
    form.id.fold(appSubjectUser.save())(v => appSubjectUser.update())
    Left(ErrorCodes.SUCCESS)
  }

  def del(form: AppSubjectUserForm) = {
    form.id match {
      case Some(v) => {
        AppSubjectUser.finder.byId(v).delete()
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.BUSIZ_FAILURE)
      }
    }
  }

  def expression(expr: ExpressionList[AppSubjectUser], form: AppSubjectUserForm): ExpressionList[AppSubjectUser] = {
    form.id.map(expr.eq("id", _))
    form.appId.map(expr.eq("appId", _))
    form.organNo.map(expr.eq("organNo", _))
    expr
  }
}
