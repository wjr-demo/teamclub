package models

import java.util.Date
import javax.persistence.{Transient, Entity, Id}

import com.avaje.ebean.annotation.Formula
import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-8-31.
 */
@Entity
class AppSubjectUser extends Model {

  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var username: String = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var roleType: Int = _

  @Transient
  var roleName: Int = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var organNo: String = _

  @BeanProperty
  var realname: String = _

  @BeanProperty
  var deptid: Int = _

  @Transient
  var deptName: String = _

  @BeanProperty
  var isSysAdmin: Boolean = _

  @BeanProperty
  var isDeptAdmin: Boolean =_

  @BeanProperty
  var entryTime: Date =_

  @BeanProperty
  var positiveTime: Date = _

  @BeanProperty
  var phone: String = _

  @BeanProperty
  var telephone: String = _

  def getRoleName: String = {
    if(roleType != 0)
      AppRole.finder.byId(roleType).getRolename
    else
      ""
  }

  def getDeptName: String = {
    if(deptid != 0) {
      AppDepartment.finder.byId(deptid).getDepartName
    }else {
      ""
    }
  }
}
object AppSubjectUser {
  val finder = new Finder(classOf[Int], classOf[AppSubjectUser])
}