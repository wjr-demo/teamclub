package models

import javax.persistence.{Entity, Id, Version}

import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
  * Created by Administrator on 2016/12/26.
  */
@Entity
class AppDepartment extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var departName: String = _

  @BeanProperty
  var departDesc: String = _

  @BeanProperty
  var appId: String =  _

  @BeanProperty
  @Version
  var updateVersion: Int = _

}

object AppDepartment  {
  val finder = new Finder(classOf[Int], classOf[AppDepartment])
}