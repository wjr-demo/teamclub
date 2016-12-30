package models

import javax.persistence.{Version, Id, Entity}

import play.db.ebean.Model
import play.db.ebean.Model.Finder
import play.libs.{Scala, Json}

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-12-26.
 */
@Entity
class AdminGlobalConfig extends Model {
  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var appId: String = _

  @BeanProperty
  var keyCode: String = _

  @BeanProperty
  var cfgName: String = _

  @BeanProperty
  var cfgType: String = _

  @BeanProperty
  var value: String = _

  @BeanProperty
  var des: String = _

  @Version
  @BeanProperty
  var updateVersion: Int = _
}

object AdminGlobalConfig {
  val finder = new Finder(classOf[Int], classOf[AdminGlobalConfig])

  def getByKey[T](key: String, id: T) = {
    val config = AdminGlobalConfig.finder.where().eq("keyCode", key).setMaxRows(1).findUnique()
    val listMap = Json.fromJson(Json.parse(config.getValue), classOf[java.util.List[java.util.HashMap[T, String]]])
    val result = Scala.toSeq(listMap).find( map => map.get("id") == id)
    result match {
      case Some(v) => v.get("name")
      case None => ""
    }
  }

}
