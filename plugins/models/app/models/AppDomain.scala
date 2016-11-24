package models

import java.util.Date
import javax.persistence.{Version, Entity, Id}

import com.fasterxml.jackson.annotation.JsonFormat
import play.db.ebean.Model
import play.db.ebean.Model.Finder
import play.libs.Json
import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-11-21.
 */
@Entity
class AppDomain extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var appid: String =_

  @BeanProperty
  var appkey: String = _

  @BeanProperty
  var appname: String = _

  @BeanProperty
  var enabled: Boolean = _

  @BeanProperty
  var createdAt: Date = _

  @BeanProperty
  var createdBy: String = _

  @BeanProperty
  @JsonFormat(pattern="yyyyMMddhhmmss")
  var updatedAt: Date = _

  @BeanProperty
  var updatedBy: String = _

  @Version
  var updateVersion: Int = _

}

object AppDomain {
  val finder = new Finder(classOf[Int], classOf[AppDomain])

  def main(args:Array[String]) = {
    val app = new AppDomain
    app.appid = "asdfdsf"
    app.appname = "vzxcvxcv"
    println(Json.toJson(app))
  }
}
