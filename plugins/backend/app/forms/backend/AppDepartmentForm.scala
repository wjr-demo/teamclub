package forms.backend

import models.AppDepartment

/**
  * Created by Administrator on 2016/12/26.
  */
case class AppDepartmentForm(id: Option[Int],
                             departName: Option[String],
                             departDesc: Option[String],
                             attachCode: Option[String],
                             var appId: Option[String],
                             currentPage: Int, pageSize: Int) extends  ToModel[AppDepartment]{
  def toModel(): AppDepartment = {
    val model = id.fold(new AppDepartment)(AppDepartment.finder.byId(_))
    departName.map(model.setDepartName(_))
    departDesc.map(model.setDepartDesc(_))
    appId.map(model.setAppId(_))
    attachCode map model.setAttachCode
    model
  }

}
