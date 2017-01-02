package services.backend

import com.avaje.ebean.{Ebean, ExpressionList}
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.UserDepartChangeForm
import models.{AppSubjectUser, UserDepartChange}
import play.api.Logger
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
    val trans = Ebean.beginTransaction()
    try{
      form.id match {
        case Some(v) => {
          model.update
        }
        case None => {
          model.save
        }
      }
      val appUser = AppSubjectUser.finder.byId(model.userId)
      appUser.setDeptid(model.getDepartId)
      appUser.setRoleType(model.getRoleId)
      appUser.setPhone(model.getPhone)
      appUser.setComQqNum(model.getComQqNum)
      appUser.setComQqPasswd(model.getComQqPasswd)
      appUser.setComQqPermit(model.getComQqPermit)
      appUser.setNetIp(model.getNetIp)
      appUser.setNetSpeed(model.getNetSpeed)
      appUser.setNetPermit(model.getNetPermit)
      appUser.setComputerNo(model.getComputerNo)
      appUser.setComputerPasswd(model.getComputerPasswd)
      appUser.setComputerConfig(model.getComputerConfig)
      appUser.update()
      trans.commit()
      Left(ErrorCodes.SUCCESS)
    } catch{
      case e: Exception => {
        Logger.error("", e)
        Right(ErrorCodes.of(e.getMessage))
      }
    }finally {
      trans.end()
    }
  }

  def expression(expr: ExpressionList[UserDepartChange], form: UserDepartChangeForm): Unit = {
    form.id.map(expr.eq("id", _))
    form.changeDate.map(expr.eq("changeDate", _))
    form.userId.map(expr.eq("userId", _))
    form.departId.map(expr.eq("departId", _))
    form.roleId.map(expr.eq("roleId", _))
    expr.orderBy("changeDate desc")
  }
}
