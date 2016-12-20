package plugin.backend.actions

/**
 * Created by zhangmeng on 16-11-21.
 */

import play.api.Logger
import play.api.libs.Crypto
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent.Future

case class XSession(username: String, appid: String, organNo: String)

class AuthenticatedRequest[A](val sess: XSession, request: Request[A]) extends WrappedRequest[A](request)


object Authenticated extends ActionBuilder[AuthenticatedRequest] {
  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) = {
    request.session.get("connected").map { connectedStr =>
      try{
        val ss: Array[String] = Crypto.decryptAES(connectedStr).split("\\|");
        block(new AuthenticatedRequest(XSession(ss(0), ss(1), ss(2)), request))
      }catch{
        case e: Exception => {
          Logger.error("", e)
          Future.successful(Redirect("/login/login"))
        }
      }
    } getOrElse {
      Future.successful(Redirect("/login/login"))
    }
  }
}

