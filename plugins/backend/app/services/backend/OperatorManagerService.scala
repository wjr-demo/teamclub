package services.backend

import java.util.Date

import com.avaje.ebean.{Expr, ExpressionList}
import com.fasterxml.jackson.databind.JsonNode
import com.google.common.collect.ImmutableMap
import commons.{ErrorCode, ErrorCodes}
import forms.backend.AppSubjectUserForm
import models.AppSubjectUser
import play.libs.Json
import plugin.backend.actions.XSession
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

  def add(form: AppSubjectUserForm, session: XSession): Either[ErrorCode, ErrorCode] = {
    val appSubjectUser = form.toModel()
    form.id.fold {
      if(appSubjectUser.username != null && appSubjectUser.username.trim() != "" && AppSubjectUser.finder.where().eq("username", appSubjectUser.username).findRowCount() > 0) {
        Right(ErrorCodes.of("用户名重复"))
      } else {
        appSubjectUser.setCreatedAt(new Date)
        appSubjectUser.setCreatedBy(session.appSubjectUser.id.toString)
        appSubjectUser.save()
        Left(ErrorCodes.SUCCESS)
      }
    }{
      v => {
        appSubjectUser.setUpdatedAt(new Date)
        appSubjectUser.setUpdatedBy(session.appSubjectUser.id.toString)
        appSubjectUser.update()
        Left(ErrorCodes.SUCCESS)
      }
    }
  }

  def del(form: AppSubjectUserForm) = {
    form.id match {
      case Some(v) => {
        val appuser = AppSubjectUser.finder.byId(v)
        appuser.setIsDelete(1)
        appuser.update
        Left(ErrorCodes.SUCCESS)
      }
      case None => {
        Right(ErrorCodes.BUSIZ_FAILURE)
      }
    }
  }

  def expression(expr: ExpressionList[AppSubjectUser], form: AppSubjectUserForm): ExpressionList[AppSubjectUser] = {
    form.id.map(expr.eq("id", _))
    form.realname.map(v => expr.like("realname", "%" + v + "%"))
    form.appId.map(expr.eq("appId", _))
    form.organNo.map(expr.eq("organNo", _))
    form.deptid.map(expr.eq("deptid", _))
    form.appSubjectUserCompanyAbout.map { companyAbount =>
      companyAbount.seaBirthday map { v =>
        expr.eq("month(birthday)", v)
      }
    }
    form.appSubjectUserMore.map { x =>
      x.strongPoint map { y =>
        expr.like("strongPoint", "%" + y + "%")
      }
      x.educationLevel map { y =>
        expr.eq("educationLevel", y)
      }
    }
    form.gender.map(expr.eq("gender", _))
    form.examineStatus.map(expr.eq("examineStatus", _))
    expr.ne("isDelete", 1)
    expr
  }

  def calcquitrate(sess: XSession): Either[JsonNode, ErrorCode] = {
    val full = AppSubjectUser.finder.where().eq("organNo", sess.organNo).findRowCount()
    val leave = AppSubjectUser.finder.where().eq("organNo", sess.organNo).and(Expr.ne("leaveTime", null), Expr.ne("leaveTime", "")).findRowCount()
    val unPositive = AppSubjectUser.finder.where().eq("organNo", sess.organNo).or(Expr.eq("positiveTime",null),Expr.eq("positiveTime", "")).findRowCount()
    val map = ImmutableMap.of("full", full, "leave", leave, "unPositive", unPositive)
    Left(Json.toJson(map))
  }
}
