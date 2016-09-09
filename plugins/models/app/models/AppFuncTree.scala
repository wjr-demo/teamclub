package models

import javax.persistence.{Entity, Id}

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-9-6.
 */
@Entity
class AppFuncTree extends Model {

  @Id
  @BeanProperty
  var id: Integer = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var module: String = _

  @BeanProperty
  var parent: Integer = _


}