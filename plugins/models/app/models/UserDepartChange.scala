package models

import java.util.Date
import javax.persistence.{Id, Entity}

import play.db.ebean.Model
import play.db.ebean.Model.Finder

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-29.
 */
@Entity
class UserDepartChange extends Model{
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var changeDate: Date = _

  @BeanProperty
  var userId: Int = _

  @BeanProperty
  var departId: Int = _

  @BeanProperty
  var roleId: Int = _

  @BeanProperty
  var phone: String = _

  @BeanProperty
  var wages: Int = _

  @BeanProperty
  var calcuStyle: Int = _

  @BeanProperty
  var remark: String = _

  def getDepartName: String = AppDepartment.finder.byId(departId).getDepartName

  def getRoleName: String = AppRole.finder.byId(roleId).getRolename

  def getCalcuStyleName: String = AdminGlobalConfig.getByKey("USER_PAY_STYLE", calcuStyle)
}

object UserDepartChange {
  def finder = new Finder(classOf[Int], classOf[UserDepartChange])
}