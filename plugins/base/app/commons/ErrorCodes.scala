package commons

/**
 * Created by zhangmeng on 16-9-1.
 */
case class ErrorCode(status: Int, message: String)

object ErrorCodes {
  def of(msg: String) = {
    ErrorCode(555, msg)
  }
  var SUCCESS: ErrorCode = ErrorCode(Integer.valueOf(0), "SUCCESS")
  var BAD_REQUEST: ErrorCode = ErrorCode(Integer.valueOf(400), "参数验证失败")
  var ENTITY_NOTFOUND: ErrorCode = ErrorCode(Integer.valueOf(401), "实体不存在")
  var PARAMS_JSON_INVALID: ErrorCode = ErrorCode(Integer.valueOf(403), "JSON参数无法解析")
  var BUSIZ_FAILURE: ErrorCode = ErrorCode(Integer.valueOf(404), "业务失败")
  var SYSTEM_ERROR: ErrorCode = ErrorCode(Integer.valueOf(500), "系统异常")
}
