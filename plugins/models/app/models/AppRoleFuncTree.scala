package models

import javax.persistence.Entity

import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-20.
 */

@Entity
class AppRoleFuncTree extends Model{
  @BeanProperty
  var nodeId: Int = _

  @BeanProperty
  var nodeType: Int = _

  @BeanProperty
  var appId: Int = _

  @BeanProperty
  var roleId : Int = _
}

object AppRoleFuncTree {
  val finder = new Finder(classOf[Int], classOf[AppRoleFuncTree])
}
