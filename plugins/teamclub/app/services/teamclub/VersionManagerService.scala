package services.teamclub

import com.avaje.ebean.{Page, Ebean}
import commons.{ErrorCode, ErrorCodes}
import forms.VersionManagerForm
import models.xteamclub.VersionManager
import play.libs.Scala

/**
 * Created by zhangmeng on 16-9-8.
 */
object VersionManagerService {

  def list(form: VersionManagerForm):Either[Page[VersionManager], ErrorCode] = {
    val page:Page[VersionManager] = listExpr(form)
    Left(page)
  }


  def listExpr(form: VersionManagerForm): Page[VersionManager] = {
    val where = Ebean.find(classOf[VersionManager]).where
    form.commitVersion.map{ x =>
      where.like("commitVersion", "%" + x + "%")
    }
    form.filePath.map {x =>
      where.like("filePath", "%" + x + "%")
    }
    val page = form.length.fold(where.findPagingList(10))(where.findPagingList)
    form.currentPage.fold(page.getPage(0))(page.getPage)
  }
}
