package forms.backend

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
      "ordered" -> optional(number),
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
      "phone" -> optional(text),
      "telephone" -> optional(text),
      "recordData" -> optional(mapping(
        "identifyNo" -> optional(text),
        "birthday" -> optional(longNumber),
        "marriageStatus" -> optional(number),
        "educationLevel" -> optional(number),
        "strongPoint" -> optional(text),
        "nativePlaceProv" -> optional(number),
        "nativePlaceCity" -> optional(number),
        "nativePlaceDetail" -> optional(text),
        "familyName" -> optional(text),
        "familyRelation" -> optional(text),
        "familyPhone" -> optional(text)
      )(AppSubjectUserMoreForm.apply)(AppSubjectUserMoreForm.unapply)),
      "companyAbountData" -> optional(mapping(
        "entryTime" -> optional(longNumber),
        "positiveTime" -> optional(longNumber),
        "expectedLeave" -> optional(longNumber),
        "comQqNum" -> optional(text),
        "comQqPasswd" -> optional(text),
        "comQqPermit" -> optional(text),
        "netIp" -> optional(text),
        "netSpeed" -> optional(text),
        "netPermit" -> optional(text),
        "computerNo" -> optional(text),
        "computerPasswd" -> optional(text),
        "computerConfig" -> optional(text),
        "remark" -> optional(text)
      )(AppSubjectUserCompanyAbout.apply)(AppSubjectUserCompanyAbout.unapply)),
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

  val appDepartmentMapper = Form(
    mapping(
      "id" -> optional(number),
      "departName" -> optional(text),
      "departDesc" -> optional(text),
      "attachCode" -> optional(text),
      "appId" -> optional(text),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AppDepartmentForm.apply)(AppDepartmentForm.unapply)
  )

  val adminAreaCodeMapper = Form(
    mapping(
      "id" -> optional(number),
      "areaCode" -> optional(text),
      "areaName" -> optional(text),
      "status" -> optional(boolean),
      "createdAt" -> optional(longNumber),
      "parentCode" -> optional(number),
      "all" -> optional(boolean),
      "currentPage" -> default(number, 0),
      "pageSize" -> default(number, defaultPageSize)
    )(AdminAreaCodeForm.apply)(AdminAreaCodeForm.unapply)
  )
}
