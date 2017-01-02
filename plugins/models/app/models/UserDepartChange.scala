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

  @BeanProperty
  var comQqNum: String = _ //企业qq帐号

  @BeanProperty
  var comQqPasswd: String = _ //企业qq密码

  @BeanProperty
  var comQqPermit: String = _ //企业qq权限

  @BeanProperty
  var netIp: String = _ //上网IP

  @BeanProperty
  var netSpeed: String =_ //上网速度

  @BeanProperty
  var netPermit: String = _ //网络权限

  @BeanProperty
  var computerNo: String = _ //电脑编号

  @BeanProperty
  var computerPasswd: String = _ //电脑密码

  @BeanProperty
  var computerConfig: String = _ //电脑配置

  def getDepartName: String = AppDepartment.finder.byId(departId).getDepartName

  def getRoleName: String = AppRole.finder.byId(roleId).getRolename

  def getCalcuStyleName: String = AdminGlobalConfig.getByKey("USER_PAY_STYLE", calcuStyle)
}

object UserDepartChange {
  def finder = new Finder(classOf[Int], classOf[UserDepartChange])
}