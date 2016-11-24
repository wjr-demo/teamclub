import com.fasterxml.jackson.databind.JsonNode
import play.libs.Json

/**
 * Created by zhangmeng on 16-9-1.
 */
package object commons {
  implicit def jsontoStr(json: JsonNode): String = {
    Json.stringify(json)
  }
}
