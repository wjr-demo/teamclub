package plugin.backend.actions

/**
 * Created by zhangmeng on 16-11-21.
 */

import commons.Libs
import models.AppSubjectUser
import play.api.Logger
import play.api.cache.Cache
import play.api.libs.Crypto
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.Future

case class XSession(username: String, appid: String, organNo: String, appSubjectUser: AppSubjectUser)

class AuthenticatedRequest[A](val sess: XSession, request: Request[A]) extends WrappedRequest[A](request)


object Authenticated extends ActionBuilder[AuthenticatedRequest] {
  import play.api.Play.current

  import scala.concurrent.duration._
  val cacheTime = Duration(30, MINUTES)

  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) = {
    request.session.get("connected").map { connectedStr =>
      try{
        val ss = Crypto.decryptAES(connectedStr).replace("HELLO", "").toInt
        val appSubjectUser = Cache.get(Libs.CachePrefix.LOGIN + ss).getOrElse {
          AppSubjectUser.finder.byId(ss)
        }.asInstanceOf[AppSubjectUser]
        Cache.set(Libs.CachePrefix.LOGIN + ss, appSubjectUser, cacheTime)
        block(new AuthenticatedRequest(XSession(appSubjectUser.username, appSubjectUser.appId, appSubjectUser.organNo, appSubjectUser), request))
      }catch{
        case e: Exception => {
          Logger.error("", e)
          Future.successful(InternalServerError)
        }
      }
    } getOrElse {
      Future.successful(Redirect("/login/login"))
    }
  }
}

