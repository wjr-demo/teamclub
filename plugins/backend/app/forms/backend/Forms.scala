package forms.backend

import play.api.data.Form


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
}
