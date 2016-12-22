package forms.backend

import models.{AppRoleFuncTree, AppRole}

/**
 * Created by zhangmeng on 16-12-20.
 */
case class AppRoleFuncTreeForm(nodeId: Option[Int],
                               nodeType: Option[Int],
                               var appId: Option[String],
                               roleId: Option[Int],
                               nodeIds: Option[String],
                               currentPage: Int,
                               pageSize: Int) extends ToModel[AppRoleFuncTree]{
  def toModel(): AppRoleFuncTree = {
    val appRoleFuncTree = (nodeId, appId) match {
      case (Some(v1), Some(v2)) => {
        AppRoleFuncTree.finder.where().eq("nodeId", v1).eq("roleId", v2).setMaxRows(1).findUnique
      }
      case _ => new AppRoleFuncTree
    }
    appRoleFuncTree
  }
}
