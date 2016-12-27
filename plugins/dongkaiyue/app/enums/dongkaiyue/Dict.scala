package enums.dongkaiyue

import play.api.libs.json._

/**
 * Created by zhangmeng on 16-12-27.
 */
case class Dict(id: Int, name: String)
object Dict extends App {
  implicit lazy val jsonWrites = new Writes[Dict]{
    def writes(n: Dict) =
      Json.obj(
        "id" -> n.id,
        "name" -> n.name
      )
  }

  val PAY_STYLE = List(
    Dict(1, "款到发货"),
    Dict(2, "预付总货款的30%，发货前付清余款"),
    Dict(3, "预付总货款的30%，货到付款"),
    Dict(4, "货到后一周内付款"),
    Dict(5, "提货前付款（控货）"),
    Dict(6, "发货前付清货款"),
    Dict(7, "次月5号前付清当月货款"),
    Dict(8, "次月10号前付清当月货款"),
    Dict(9, "次月15号前付清当月货款"),
    Dict(10, "次月20号前付清当月货款"),
    Dict(11, "次月10号前对账，20号前付清当月货款"),
    Dict(12, "次月30号前付清当月货款"),
    Dict(13, "货到付款")
  )

  val CUSTOMER_LEVEL = List(
    Dict(1, "A+"),
    Dict(2, "A"),
    Dict(3, "B"),
    Dict(4, "C"),
    Dict(5, "D"),
    Dict(6, "E")
  )

  val FILE_TRANSFER_STYLE = List(
    Dict(1, "传真方式"),
    Dict(2, "邮件方式"),
    Dict(3, "Q Q 方式"),
    Dict(4, "电话方式"),
    Dict(5, "扫描方式")
  )

  println(Json.toJson(PAY_STYLE))
  println(Json.toJson(CUSTOMER_LEVEL))
  println(Json.toJson(FILE_TRANSFER_STYLE))
}
