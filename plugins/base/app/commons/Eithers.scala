package commons

import play.api.libs.json._

/**
 * Created by zhangmeng on 16-9-2.
 */
object Eithers {
  val success = {
    Json.toJson(ErrorCodes.SUCCESS)
  }

  def failure(errorCode: ErrorCode) = {
    Json.toJson(errorCode)
  }

  def failure(msg: String) = {
    Json.toJson(ErrorCode(500, msg))
  }

}
