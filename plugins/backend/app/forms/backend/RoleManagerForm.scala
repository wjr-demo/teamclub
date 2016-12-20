package forms.backend

import java.util.Date

import models.AppRole
import plugin.backend.actions.XSession

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-19.
 */
case class RoleManagerForm(id: Option[Int],
                           rolename: Option[String],
                           description: Option[String],
                           var appid: Option[String],
                           var organNo: Option[String],
                           var createdAt: Option[Long],
                           var createdBy: Option[String],
                           currentPage: Int,
                           pageSize: Int = 10) extends ToModel[AppRole]{
  def toModel(): AppRole = {
    val model = id.fold(new AppRole)(AppRole.finder.byId(_))
    rolename.map(model.setRolename)
    description.map(model.setDescription)
    appid.map(model.setAppId)
    organNo.map(model.setOrganNo)
    createdAt.map(v => model.setCreatedAt(new Date(v)))
    createdBy.map(model.setCreatedBy)
    model
  }

}
