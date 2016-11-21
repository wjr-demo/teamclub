package plugin.backend.actions

/**
 * Created by zhangmeng on 16-11-21.
 */
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent.Future

case class XSession(username: String, appid: String)

class AuthenticatedRequest[A](val sess: XSession, request: Request[A]) extends WrappedRequest[A](request)


object Authenticated extends ActionBuilder[AuthenticatedRequest] {
  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) = {
    request.session.get("connected").map { username =>
      block(new AuthenticatedRequest(XSession(username, "backend"), request))
    } getOrElse {
      Future.successful(Redirect("/backend/login"))
    }
  }
}

