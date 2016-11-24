package forms.backend

import java.util.Date

import play.api.data.Form
import play.api.data.Forms._

/**
 * Created by zhangmeng on 16-11-22.
 */
object Forms {
  def required[T](form:Form[T], keys: Array[String]) = {
    var copyForm = form
    for(key <- keys) {
      if(form.data.get(key) == null || !form.data.get(key).isDefined) {
        copyForm = copyForm.withError(key, s"${key} is required")
      }
    }
    copyForm
  }

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
}
