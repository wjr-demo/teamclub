package models

import java.util.Date
import javax.persistence.{Version, Id, Entity}

import com.fasterxml.jackson.annotation.JsonFormat
import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-16.
 */
@Entity
class AppRole extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var rolename: String = _

  @BeanProperty
  var description: String = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var organNo: String = _


  @BeanProperty
  var createdAt: Date = _

  @BeanProperty
  var createdBy: String = _

  @Version
  @BeanProperty
  var updateVersion: Int = _

}

object AppRole {

  def finder = new Finder(classOf[Int], classOf[AppRole])

}

