package forms.backend

import models.AppSubjectUser

/**
 * Created by zhangmeng on 16-12-19.
 */
case class AppSubjectUserForm(id: Option[Int],
                              username: Option[String],
                              appId: Option[String],
                              organNo: Option[String],
                              realname: Option[String],
                              currentPage: Int,
                              pageSize: Int) extends ToModel[AppSubjectUser]{
  def toModel(): AppSubjectUser = {
    val appSubjectUser = id.fold(new AppSubjectUser)(AppSubjectUser.finder.byId(_))
    username map appSubjectUser.setUsername
    appId map appSubjectUser.setAppId
    organNo map appSubjectUser.setOrganNo
    realname map appSubjectUser.setRealname
    appSubjectUser
  }
}
