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
  var username: String = _ //用户名

  @BeanProperty
  var password: String = _ //密码

  @BeanProperty
  var appId: String = _ //平台号

  @BeanProperty
  var roleType: Int = _ //角色

  @BeanProperty
  var organNo: String = _ //所属组织机构（公司）

  @BeanProperty
  var realname: String = _ //真实姓名

  @BeanProperty
  var deptid: Int = _ //部门id

  @BeanProperty
  var isSysAdmin: Boolean = _ //是否系统管理员

  @BeanProperty
  var isDeptAdmin: Boolean =_ //是否部门管理员

  @BeanProperty
  var phone: String = _ //座机

  @BeanProperty
  var telephone: String = _ //手机号码

  @BeanProperty
  var entryTime: Date =_ //入职时间

  @BeanProperty
  var positiveTime: Date = _ //转正时间

  @BeanProperty
  var identifyNo: String = _  //身份证号

  @BeanProperty
  var birthday: Date = _ //生日

  @BeanProperty
  var marriageStatus: Int = _ //婚姻状态 { 1:未婚 , 2:已婚 }

  @BeanProperty
  var educationLevel: Int = _  //教育程度

  @BeanProperty
  var strongPoint: String = _ //特长

  @BeanProperty
  var nativePlaceProv: Int = _ //籍贯省

  @BeanProperty
  var nativePlaceCity: Int = _ //籍贯市

  @BeanProperty
  var nativePlaceDetail: String = _ //籍贯详细地址

  @BeanProperty
  var familyName: String = _ //亲属姓名

  @BeanProperty
  var familyRelation: String = _ //亲属关系

  @BeanProperty
  var familyPhone: String = _ //亲属号码

  @BeanProperty
  var expectedLeave: Date = _ //预期离职时间

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

  @BeanProperty
  var remark: String =_ //备注

  @BeanProperty
  var awardRecord: String = _ // 奖励记录

  @BeanProperty
  var breakRuleRecord: String = _ //违纪记录

  @BeanProperty
  var examineStatus: Int = _ //审核状态

  @BeanProperty
  var leaveTime: Date = _  //离职时间

  @BeanProperty
  var gender: Int = _ // 1 男 2 女

  @BeanProperty
  var isDelete: Int = _

  @BeanProperty
  var createdAt: Date = _

  @BeanProperty
  var createdBy: String = _

  @BeanProperty
  var updatedAt: Date = _

  @BeanProperty
  var updatedBy: String = _

  @Transient
  var roleName: String = _ //角色名称

  @Transient
  var deptAttachCode:String = _ //部门Attach Code

  @Transient
  var roleAttachCode: String = _ //角色 Attach Code

  @Transient
  var deptName: String = _ //部门名称

  def getRoleAttachCode: String =  if(roleType != 0) AppRole.finder.byId(roleType).getAttachCode else ""

  def getRoleName: String = if(roleType != 0) AppRole.finder.byId(roleType).getRolename else ""

  def getDeptAttachCode: String = if(deptid != 0) AppDepartment.finder.byId(deptid).getAttachCode else ""

  def getDeptName: String = if(deptid != 0)  AppDepartment.finder.byId(deptid).getDepartName else ""

  def getDepartment: AppDepartment = AppDepartment.finder.byId(deptid)

  def getRole: AppRole = AppRole.finder.byId(roleType)

}
object AppSubjectUser {
  val finder = new Finder(classOf[Int], classOf[AppSubjectUser])
}