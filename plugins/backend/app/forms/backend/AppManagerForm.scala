package forms.backend

import java.util.Date

import models.AppDomain

/**
 * Created by zhangmeng on 16-11-21.
 */

case class AppManagerForm(id: Option[Int],
                          appid: Option[String],
                          appkey: Option[String],
                          appname: Option[String],
                          enabled: Option[Boolean],
                          var createdAt: Option[Long],
                          var createdBy: Option[String],
                          var updatedAt:Option[Long],
                          var updatedBy:Option[String],
                          currentPage: Int,
                          pageSize: Int = 10) extends ToModel[AppDomain]{

  def toModel(): AppDomain = {
    val appDomain = id match {
      case Some(v) => AppDomain.finder.byId(v)
      case None => new AppDomain
    }
    appid.map {v => appDomain.setAppid(v)}
    appkey.map {v => appDomain.setAppkey(v)}
    appname.map {v => appDomain.setAppname(v)}
    enabled.map {v => appDomain.setEnabled(v)}
    createdAt.map {v => appDomain.setCreatedAt(new Date(v))}
    createdBy.map {v => appDomain.setCreatedBy(v)}
    updatedAt.map {v => appDomain.setUpdatedAt(new Date(v))}
    updatedBy.map {v => appDomain.setUpdatedBy(v)}
    appDomain
  }
}
