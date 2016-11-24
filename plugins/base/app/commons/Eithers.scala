package commons

import com.fasterxml.jackson.databind.JsonNode
import play.api.data.Form
import play.libs.Json

/**
 * Created by zhangmeng on 16-9-2.
 */
object Eithers {
  val success:String = {
    Json.toJson(ErrorCodes.SUCCESS)
  }

  def failure(errorCode: ErrorCode):String = {
    Json.toJson(errorCode)
  }

  def failure(msg: String):String = {
    Json.toJson(new ErrorCode(500, msg))
  }

  def failure[T](form: Form[T]):String = {
    Json.toJson(new ErrorCode(500,form.errorsAsJson.toString))
  }

  def toJson[T](ei: Either[T, ErrorCode]): String = {
    ei match {
      case Left(l) => Json.toJson(l)
      case Right(r) => Json.toJson(r)
    }
  }

  def main(args:Array[String]) = {
    println(success)
  }
}
