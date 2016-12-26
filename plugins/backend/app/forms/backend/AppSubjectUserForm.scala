package forms.backend

import models.AppSubjectUser
import play.api.libs.Crypto

/**
 * Created by zhangmeng on 16-12-19.
 */
case class AppSubjectUserForm(id: Option[Int],
                              username: Option[String],
                              password: Option[String],
                              var appId: Option[String],
                              roleType: Option[Int],
                              organNo: Option[String],
                              realname: Option[String],
                              deptid: Option[Int],
                              isSysAdmin: Option[Boolean],
                              isDeptAdmin: Option[Boolean],
                              currentPage: Int,
                              pageSize: Int) extends ToModel[AppSubjectUser]{
  def toModel(): AppSubjectUser = {
    val appSubjectUser = id.fold {
      new AppSubjectUser
    }{
      AppSubjectUser.finder.byId(_)
    }
    username map appSubjectUser.setUsername
    password map { v =>
      appSubjectUser.setPassword(Crypto.encryptAES(v))
    }
    appId map appSubjectUser.setAppId
    roleType map appSubjectUser.setRoleType
    organNo map appSubjectUser.setOrganNo
    realname map appSubjectUser.setRealname
    deptid map appSubjectUser.setDeptid
    isSysAdmin map appSubjectUser.setIsSysAdmin
    isDeptAdmin map appSubjectUser.setIsDeptAdmin
    appSubjectUser
  }
}
