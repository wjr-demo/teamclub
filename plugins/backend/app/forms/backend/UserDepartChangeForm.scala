package forms.backend

import java.util.Date

import models.UserDepartChange

/**
 * Created by zhangmeng on 16-12-29.
 */
case class UserDepartChangeForm(id: Option[Int],
                                changeDate: Option[Long],
                                userId: Option[Int],
                                departId: Option[Int],
                                roleId: Option[Int],
                                phone: Option[String],
                                wages: Option[Int],
                                calcuStyle: Option[Int],
                                remark: Option[String],
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
    model
  }
}
