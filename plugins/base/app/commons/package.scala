import com.fasterxml.jackson.databind.JsonNode
import play.api.libs.functional.syntax._
import play.api.libs.json._
/**
 * Created by zhangmeng on 16-9-1.
 */
package object commons {
  implicit val errorcodeWriters: Writes[ErrorCode] = (
    (JsPath \ "status").write[Int] and
    (JsPath \ "message").write[String]
  )(unlift(ErrorCode.unapply))

  implicit val errorcodeReaders: Reads[ErrorCode] = (
    (JsPath \ "status").read[Int] and
    (JsPath \ "message").read[String]
  )(ErrorCode.apply _)

  implicit def convertJ(json: JsValue): JsonNode = {
    play.libs.Json.parse(json.toString)
  }

}
