package forms.backend

import play.api.data.Form
import play.api.data.Forms._

/**
 * Created by zhangmeng on 16-12-13.
 */
object FormMappers {
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
      "pageSize" -> default(number, 10)
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
      "pageSize" -> default(number, 10)
    )(TreeManagerForm.apply)(TreeManagerForm.unapply)
  )
}
