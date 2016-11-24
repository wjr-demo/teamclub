package commons

import play.libs.Json

import scala.beans.BeanProperty


/**
 * Created by zhangmeng on 16-9-1.
 */
class ErrorCode(@BeanProperty val status: Int,@BeanProperty val message:String) {
}


object ErrorCodes {
  def of(msg: String) = {
    new ErrorCode(555, msg)
  }
  var SUCCESS: ErrorCode = new ErrorCode(Integer.valueOf(0), "SUCCESS")
  var BAD_REQUEST: ErrorCode = new ErrorCode(Integer.valueOf(400), "参数验证失败")
  var ENTITY_NOTFOUND: ErrorCode = new ErrorCode(Integer.valueOf(401), "实体不存在")
  var PARAMS_JSON_INVALID: ErrorCode = new ErrorCode(Integer.valueOf(403), "JSON参数无法解析")
  var BUSIZ_FAILURE: ErrorCode = new ErrorCode(Integer.valueOf(404), "业务失败")
  var SYSTEM_ERROR: ErrorCode = new ErrorCode(Integer.valueOf(500), "系统异常")

  def main(args: Array[String]) = {
    println(Json.toJson(SUCCESS))
  }
}
