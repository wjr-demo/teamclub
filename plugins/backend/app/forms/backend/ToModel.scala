package forms.backend

import plugin.backend.actions.XSession

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-19.
 */
trait ToModel[T] {

  def toModel(): T

}
