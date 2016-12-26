package forms.backend

import models.AdminGlobalConfig
import play.api.data.Form
import play.api.data.Forms._

/**
 * Created by zhangmeng on 16-12-13.
 */
object FormMappers {

  val defaultPageSize = 10

  val appManagerFormMapper = Form(
    mapping(
      "id" -> optional(number),
      "appid" -> optional(text),
      "appkey" -> optional(text),
      "appname" -> optional(text),
      "enabled" -> optional(boolean),
      "createdAt" -> optional(longNumber),
      "createBy" -> optional(text),
      "updatedAt" -> optional(longNumber),
      "updatedBy" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AppManagerForm.apply)(AppManagerForm.unapply)
  )

  val treeManagerFormMapper = Form(
    mapping(
      "id" -> optional(number),
      "name" -> optional(text),
      "appId" -> optional(text),
      "parent" -> optional(number),
      "module" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(TreeManagerForm.apply)(TreeManagerForm.unapply)
  )

  val roleManagerFormMapper = Form(
    mapping(
      "id" -> optional(number),
      "rolename" -> optional(text),
      "description" -> optional(text),
      "appid" -> optional(text),
      "organNo" -> optional(text),
      "createdAt" -> optional(longNumber),
      "createdBy" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(RoleManagerForm.apply)(RoleManagerForm.unapply)
  )

  val appSubjectUserMapper = Form(
    mapping(
      "id" -> optional(number),
      "username" -> optional(text),
      "password" -> optional(text),
      "appId" -> optional(text),
      "roleType" -> optional(number),
      "organNo" -> optional(text),
      "realname" -> optional(text),
      "deptid" -> optional(number),
      "isSysAdmin" -> optional(boolean),
      "isDeptAdmin" -> optional(boolean),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AppSubjectUserForm.apply)(AppSubjectUserForm.unapply)
  )

  val appRoleFuncTreeMapper = Form(
    mapping(
      "nodeId" -> optional(number),
      "nodeType" -> optional(number),
      "appId" -> optional(text),
      "roleId" -> optional(number),
      "nodeIds" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AppRoleFuncTreeForm.apply)(AppRoleFuncTreeForm.unapply)
  )

  val adminGlobalConfigMapper = Form(
    mapping(
      "id" -> optional(number),
      "appId" -> optional(text),
      "keyCode" -> optional(text),
      "cfgName" -> optional(text),
      "cfgType" -> optional(text),
      "value" -> optional(text),
      "des" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AdminGlobalConfigForm.apply)(AdminGlobalConfigForm.unapply)
  )
}
