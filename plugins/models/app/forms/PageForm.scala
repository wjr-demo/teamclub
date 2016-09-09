package forms

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-9-8.
 */
class PageForm(start:Option[Int], length: Option[Int]) {
  def currentPage:Option[Int] = {
    if(start.isDefined && length.isDefined) {
      Some(start.get / length.get)
    }else{
      None
    }
  }
}
