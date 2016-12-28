package forms.backend

import java.util.Date

import models.AdminAreaCode

/**
 * Created by zhangmeng on 16-12-28.
 */
case class AdminAreaCodeForm(id: Option[Int],
                             areaCode: Option[String],
                             areaName: Option[String],
                             status:Option[Boolean],
                             createdAt: Option[Long],
                             parentCode: Option[Int],
                             currentPage: Int,
                             pageSize: Int) extends ToModel[AdminAreaCode]{
  def toModel(): AdminAreaCode = {
    val model = id.fold ( new AdminAreaCode) (AdminAreaCode.finder.byId(_))
    areaCode map (model.setAreaCode)
    areaName map (model.setAreaName)
    status map (model.setStatus)
    createdAt map (v => model.setCreatedAt(new Date(v)))
    parentCode map model.setParentCode
    model
  }
}
