package forms.backend

import models.AppFuncTree

/**
 * Created by zhangmeng on 16-11-28.
 */
case class TreeManagerForm(id: Option[Int],
                           name: Option[String],
                           appId: Option[String],
                           parent: Option[Int],
                           ordered: Option[Int],
                           module: Option[String],
                           currentPage: Int,
                           pageSize: Int = 10) extends ToModel[AppFuncTree]{
  def toModel(): AppFuncTree = {
    val appFuncTree: AppFuncTree = id match {
      case Some(v) => AppFuncTree.finder.byId(v)
      case None => new AppFuncTree
    }
    name map { v => appFuncTree.setName(v) }
    appId map {v => appFuncTree.setAppId(v) }
    module map {v => appFuncTree.setModule(v) }
    parent map {v => appFuncTree.setParent(v) }
    ordered.map(appFuncTree.setOrdered(_))
    appFuncTree
  }
}