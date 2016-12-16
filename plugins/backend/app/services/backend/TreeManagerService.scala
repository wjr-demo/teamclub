package services.backend

import com.avaje.ebean.{Expr, ExpressionList}
import com.fasterxml.jackson.databind.JsonNode
import com.google.common.collect.ImmutableMap
import commons.{ErrorCode, ErrorCodes}
import forms.backend.TreeManagerForm
import models.AppFuncTree
import play.libs.{Json, Scala}
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-11-28.
 */
object TreeManagerService {
  def list(treeManagerForm: TreeManagerForm): Either[JsonNode, ErrorCode] = {
    val expr = AppFuncTree.finder.where()
    expression(expr, treeManagerForm)
    Left(AppFuncTree.toJson(expr.findList()))
  }

  def add(treeManagerForm: TreeManagerForm): Either[ErrorCode, ErrorCode] = {
    val model = treeManagerForm.toModel()
    if(model.id == 0) {
      model.save()
    }else {
      model.update()
    }
    Left(ErrorCodes.SUCCESS)
  }

  def del(treeManagerForm: TreeManagerForm): Either[ErrorCode, ErrorCode] = {
    val model = treeManagerForm.toModel()
    if( AppFuncTree.finder.where().eq("parent", model.getId).findRowCount() > 0) {
      Right(ErrorCodes.of("有子菜单，不允许删除"))
    }else {
      model.delete()
      Left(ErrorCodes.SUCCESS)
    }
  }

  def parentlist(treeManagerForm: TreeManagerForm): Either[JsonNode, ErrorCode] = {
    val list = AppFuncTree.finder.where().or(Expr.eq("parent", 0), Expr.eq("parent", null)).findList()
    val tuple = Scala.toSeq(list).collect({
      case v: AppFuncTree => ImmutableMap.of("id", v.id ,"name", v.getName)
    })
    Left(Json.toJson(Scala.asJava(tuple)))
  }

  def expression(expr: ExpressionList[AppFuncTree], form: TreeManagerForm): ExpressionList[AppFuncTree] = {
    form.id.map { v => expr.eq("id", v)}
    expr
  }

}
