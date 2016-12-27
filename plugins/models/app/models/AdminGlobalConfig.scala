package models

import javax.persistence.{Version, Id, Entity}

import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-26.
 */
@Entity
class AdminGlobalConfig extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var keyCode: String = _

  @BeanProperty
  var cfgName: String = _

  @BeanProperty
  var cfgType: String = _

  @BeanProperty
  var value: String = _

  @BeanProperty
  var des: String = _

  @Version
  @BeanProperty
  var updateVersion: Int = _
}

object AdminGlobalConfig {
  val finder = new Finder(classOf[Int], classOf[AdminGlobalConfig])
}
