package models

import javax.persistence.{Id, Entity}

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-27.
 */
@Entity
class OrgEntityInfo extends Model{
  @Id
  @BeanProperty
  var id:Int = _

  @BeanProperty
  var organNo: String = _

  @BeanProperty
  var organName: String = _


}
