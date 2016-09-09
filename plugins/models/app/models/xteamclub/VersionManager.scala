package models.xteamclub

import java.util.Date
import javax.persistence.{Entity, Id, Table, Version}

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-9-8.
 */
@Entity
@Table(name="teamclub_version_manager")
class VersionManager extends Model{
  @BeanProperty
  @Id
  var id: Integer = _

  @BeanProperty
  var commitVersion: String = _

  @BeanProperty
  var filePath: String = _

  @BeanProperty
  @Version
  var updateVersion:Date = _
}

object VersionManager {
  import play.api.libs.json._
  implicit val locationWrites = new Writes[VersionManager] {
    def writes(versionManager: VersionManager) = Json.obj(
      "commitVersion" -> versionManager.commitVersion,
      "filePath" -> versionManager.filePath
    )
  }
}