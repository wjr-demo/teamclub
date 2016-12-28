package models

import java.util.Date
import javax.persistence.{Id, Entity}

import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-28.
 */
@Entity
class AdminAreaCode extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var areaCode: String = _

  @BeanProperty
  var areaName: String = _

  @BeanProperty
  var status: Boolean = _

  @BeanProperty
  var createdAt: Date = _

  @BeanProperty
  var parentCode: Int = _
}

object AdminAreaCode {
  val finder = new Finder(classOf[Int], classOf[AdminAreaCode])
}
