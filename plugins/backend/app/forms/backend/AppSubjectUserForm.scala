package forms.backend

import java.util.Date

import models.AppSubjectUser
import play.api.libs.Crypto

/**
 * Created by zhangmeng on 16-12-19.
 */

case class AppSubjectUserMoreForm(identifyNo: Option[String],
                                  birthday: Option[Long],
                                  marriageStatus: Option[Int],
                                  educationLevel: Option[Int],
                                  strongPoint: Option[String],
                                  nativePlaceDetail: Option[String],
                                  familyName: Option[String],
                                  familyRelation: Option[String],
                                  familyPhone: Option[String])

case class AppSubjectUserCompanyAbout(entryTime: Option[Long],
                                      positiveTime:Option[Long],
                                      expectedLeave: Option[Long],
                                      comQqNum: Option[String],
                                      comQqPasswd: Option[String],
                                      comQqPermit: Option[String],
                                      netIp: Option[String],
                                      netSpeed: Option[String],
                                      netPermit: Option[String],
                                      computerNo: Option[String],
                                      computerPasswd: Option[String],
                                      computerConfig: Option[String],
                                      remark: Option[String])

case class AppSubjectUserForm(id: Option[Int],
                              username: Option[String],
                              password: Option[String],
                              var appId: Option[String],
                              roleType: Option[Int],
                              organNo: Option[String],
                              realname: Option[String],
                              deptid: Option[Int],
                              isSysAdmin: Option[Boolean],
                              isDeptAdmin: Option[Boolean],
                              phone: Option[String],
                              telephone: Option[String],
                              appSubjectUserMore: Option[AppSubjectUserMoreForm],
                              appSubjectUserCompanyAbout: Option[AppSubjectUserCompanyAbout],
                              currentPage: Int,
                              pageSize: Int) extends ToModel[AppSubjectUser]{
  def toModel(): AppSubjectUser = {
    val appSubjectUser = id.fold {
      new AppSubjectUser
    }{
      AppSubjectUser.finder.byId(_)
    }
    username map appSubjectUser.setUsername
    password map { v =>
      appSubjectUser.setPassword(Crypto.encryptAES(v))
    }
    appId map appSubjectUser.setAppId
    roleType map appSubjectUser.setRoleType
    organNo map appSubjectUser.setOrganNo
    realname map appSubjectUser.setRealname
    deptid map appSubjectUser.setDeptid
    isSysAdmin map appSubjectUser.setIsSysAdmin
    isDeptAdmin map appSubjectUser.setIsDeptAdmin
    appSubjectUserMore map { x =>
      x.identifyNo.map{ y =>  appSubjectUser.setIdentifyNo(y) }
      x.birthday map { y => appSubjectUser.setBirthday(new Date(y))}
      x.marriageStatus map { y => appSubjectUser.setMarriageStatus(y)}
      x.educationLevel map { y => appSubjectUser.setEducationLevel(y)}
      x.strongPoint map { y => appSubjectUser.setStrongPoint(y)}
      x.nativePlaceDetail.map { y => appSubjectUser.setNativePlaceDetail(y)}
      x.familyName map { y => appSubjectUser.setFamilyName(y)}
      x.familyRelation map { y => appSubjectUser.setFamilyRelation(y)}
      x.familyPhone map { y => appSubjectUser.setFamilyPhone(y)}
    }
    appSubjectUserCompanyAbout map { x =>
      x.entryTime map { y => appSubjectUser.setEntryTime(new Date(y))}
      x.positiveTime map { y => appSubjectUser.setPositiveTime(new Date(y))}
      x.expectedLeave map { y => appSubjectUser.setExpectedLeave(new Date(y))}
      x.comQqNum map { y => appSubjectUser.setComQqNum(y)}
      x.comQqPasswd map { y => appSubjectUser.setComQqPasswd(y)}
      x.comQqPermit map { y => appSubjectUser.setComQqPermit(y)}
      x.netIp map { y => appSubjectUser.setNetIp(y)}
      x.netSpeed map { y => appSubjectUser.setNetSpeed(y)}
      x.netPermit map { y => appSubjectUser.setNetPermit(y)}
      x.computerNo map { y => appSubjectUser.setComputerNo(y)}
      x.computerPasswd map { y => appSubjectUser.setComputerPasswd(y)}
      x.computerConfig map { y => appSubjectUser.setComputerConfig(y)}
      x.remark map { y => appSubjectUser.setRemark(y)}
    }
    appSubjectUser
  }
}
