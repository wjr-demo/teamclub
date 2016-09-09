package controllers.backend

/**
 * Created by zhangmeng on 16-9-7.
 */


import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent.Future

class AuthenticatedRequest[A](val username: String, request: Request[A]) extends WrappedRequest[A](request)


object Authenticated extends ActionBuilder[AuthenticatedRequest] {
  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[SimpleResult]) = {
    request.session.get("connected").map { username =>
      block(new AuthenticatedRequest(username, request))
    } getOrElse {
      Future.successful(Redirect("/backend/login"))
    }
  }
}