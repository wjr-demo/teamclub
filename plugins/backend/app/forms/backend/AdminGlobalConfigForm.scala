package forms.backend

import models.AdminGlobalConfig

/**
 * Created by zhangmeng on 16-12-26.
 */
case class AdminGlobalConfigForm(id: Option[Int],
                                 var appId: Option[String],
                                 keyCode: Option[String],
                                 cfgName: Option[String],
                                 cfgType: Option[String],
                                 value: Option[String],
                                 des: Option[String],
                                 currentPage: Int,
                                 pageSize: Int) extends ToModel[AdminGlobalConfig]{
  def toModel(): AdminGlobalConfig = {
    val model = id match {
      case Some(v) => AdminGlobalConfig.finder.byId(v)
      case None => new AdminGlobalConfig
    }
    appId map model.setAppId
    keyCode map model.setKeyCode
    cfgName map model.setCfgName
    cfgType map model.setCfgType
    value map model.setValue
    des map model.setDes
    model
  }
}
