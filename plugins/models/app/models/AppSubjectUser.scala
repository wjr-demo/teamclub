package models

import play.db.ebean.Model
import javax.persistence.Entity
import javax.persistence.Table

import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-8-31.
 */
@Entity
class AppSubjectUser extends Model {

  @BeanProperty
  var id: Integer = _

  @BeanProperty
  var username: String = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var password: String = _

}
object AppSubjectUser {
  val finder = new Finder(classOf[Int], classOf[AppSubjectUser])
}