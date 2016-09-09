package forms

import com.avaje.ebean.Ebean
import models.xteamclub.VersionManager
import play.api.data.Form
import play.api.data.Forms._
/**
 * Created by zhangmeng on 16-9-9.
 */
case class VersionManagerForm(start:Option[Int], length: Option[Int],id: Option[Int], commitVersion: Option[String], filePath: Option[String]) extends PageForm(start, length)

object VersionManagerForm {
  implicit val versionManagerForm = Form(
    mapping(
      "start" -> optional(number),
      "length" -> optional(number),
      "id" -> optional(number),
      "commitVersion" -> optional(text),
      "filePath" -> optional(text)
      )(VersionManagerForm.apply)(VersionManagerForm.unapply)
  )
  implicit def convertVersionManager(form: VersionManagerForm): Option[VersionManager] = {
    try{
      val versionManager = form.id.map(id => Ebean.find(classOf[VersionManager], id)).getOrElse(new VersionManager)
      form.commitVersion.map(versionManager.setCommitVersion(_))
      form.filePath.map(versionManager.setFilePath(_))
      Some(versionManager)
    }catch{
      case e: Exception => None
    }

  }

}