package services.backend

import commons.ErrorCode
import play.libs.F
import java.util.Map

/**
 * Created by zhangmeng on 16-9-7.
 */
trait ILogin {
  def loginHtml: String

  def loginInvoke(username: String, password: String): F.Either[String, ErrorCode]
}