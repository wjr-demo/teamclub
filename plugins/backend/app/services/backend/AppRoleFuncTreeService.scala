package services.backend

import com.avaje.ebean.{Ebean, ExpressionList}
import com.avaje.ebean.annotation.Transactional
import com.fasterxml.jackson.databind.JsonNode
import commons.{ErrorCodes, ErrorCode}
import forms.backend.{TreeManagerForm, AppRoleFuncTreeForm}
import models.{AppSubjectUser, AppFuncTree, AppRoleFuncTree, AppDomain}
import play.libs.Scala
import plugins.ebean.Paging

/**
 * Created by zhangmeng on 16-12-20.
 */
object AppRoleFuncTreeService {
  def list(form: AppRoleFuncTreeForm) = {
    val expr = AppRoleFuncTree.finder.where();
    expression(expr, form)
    val page = expr.findPagingList(form.pageSize).getPage(form.currentPage)
    Left(Paging.toPage(page).toJson)
  }

  @Transactional
  def add(form: AppRoleFuncTreeForm): Either[ErrorCode, ErrorCode] = {
    form.roleId match{
      case None => Right(ErrorCodes.BUSIZ_FAILURE)
      case Some(v1) => {
        Ebean.createUpdate(classOf[AppRoleFuncTree],"delete from AppRoleFuncTree where roleId = :roleId").setParameter("roleId", v1).execute();
        form.nodeIds.map { v=>
          val nodeArray = v.split(",")
           for(node <- nodeArray) {
            val single = new AppRoleFuncTree()
            form.appId.map{ single.setAppId(_)}
            single.setNodeId(node.toInt)
            single.setRoleId(v1)
            single.save()
          }
        }
        Left(ErrorCodes.SUCCESS)
      }
    }
  }

  def treeRoleList(roleId: Int, appSubjectUser: AppSubjectUser): Either[JsonNode, ErrorCode] =  {
    val appFuncTrees:Option[java.util.List[AppFuncTree]] = if(appSubjectUser.getIsSysAdmin) {
      Some(Ebean.find(classOf[AppFuncTree]).where().eq("appId", appSubjectUser.appId).orderBy("ordered asc").findList)
    }else {
      val appRoleFuncTrees = AppRoleFuncTree.finder.where().eq("roleId",appSubjectUser.getDeptid).findList()
      val nodeList = Scala.toSeq(appRoleFuncTrees).map{ v => v.getNodeId}
      Some(AppFuncTree.finder.where().eq("appId", appSubjectUser.appId).in("id", Scala.asJava(nodeList)).orderBy("ordered asc").findList())
    }
    appFuncTrees match {
      case None => Right(ErrorCodes.of("空菜单"))
      case Some(list) => {
        val scaList = Scala.toSeq(list)
        val newScaList = scaList.map { single =>
          val appRoleFuncTree = AppRoleFuncTree.finder.where().eq("nodeId", single.getId).eq("roleId", roleId).setMaxRows(1).findUnique()
          single.setState(if (appRoleFuncTree == null) false else true)
          single
        }
        Left(AppFuncTree.toJson(Scala.asJava(newScaList)))
      }
    }
  }

  def expression(expr: ExpressionList[AppRoleFuncTree], form: AppRoleFuncTreeForm): ExpressionList[AppRoleFuncTree] = {
    form.nodeId.map(expr.eq("nodeId", _))
    form.roleId.map(expr.eq("roleId", _))
    expr
  }
}
