package forms.backend

import java.util.Date

import models.UserDepartChange

/**
 * Created by zhangmeng on 16-12-29.
 */
case class UserDepartChangeAnother(comQqNum: Option[String],
                                   comQqPasswd: Option[String],
                                   comQqPermit: Option[String],
                                   netIp: Option[String],
                                   netSpeed: Option[String],
                                   netPermit: Option[String],
                                   computerNo: Option[String],
                                   computerPasswd: Option[String],
                                   computerConfig: Option[String],
                                   remark: Option[String]
                                  )
case class UserDepartChangeForm(id: Option[Int],
                                var changeDate: Option[Long],
                                userId: Option[Int],
                                departId: Option[Int],
                                roleId: Option[Int],
                                phone: Option[String],
                                wages: Option[Int],
                                calcuStyle: Option[Int],
                                remark: Option[String],
                                userDepartChangeAnother: Option[UserDepartChangeAnother],
                                currentPage: Int,
                                pageSize: Int = 10) extends ToModel[UserDepartChange]{
  def toModel(): UserDepartChange = {
    val model = id match {
      case Some(v) => UserDepartChange.finder.byId(v)
      case None => new UserDepartChange
    }
    changeDate map { v => model.setChangeDate(new Date(v)) }
    userId map model.setUserId
    departId map model.setDepartId
    roleId map model.setRoleId
    phone map model.setPhone
    wages map model.setWages
    calcuStyle map model.setCalcuStyle
    remark map model.setRemark
    userDepartChangeAnother map {
      x => {
        x.comQqNum map { y => model.setComQqNum(y)}
        x.comQqPasswd map { y => model.setComQqPasswd(y)}
        x.comQqPermit map { y => model.setComQqPermit(y)}
        x.netIp map { y => model.setNetIp(y)}
        x.netSpeed map { y => model.setNetSpeed(y)}
        x.netPermit map { y => model.setNetPermit(y)}
        x.computerNo map { y => model.setComputerNo(y)}
        x.computerPasswd map { y => model.setComputerPasswd(y)}
        x.computerConfig map { y => model.setComputerConfig(y)}
        x.remark map {y => model.setRemark(y)}
      }
    }
    model
  }
}
