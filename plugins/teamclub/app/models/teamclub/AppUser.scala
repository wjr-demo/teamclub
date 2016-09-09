package models.teamclub

import javax.persistence.Entity

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-8-30.
 */

@Entity
class AppUser extends Model{
  @BeanProperty
  var id:Integer = _
  @BeanProperty
  var userKey: String = _
  @BeanProperty
  var lowerUserName: String = _

}
