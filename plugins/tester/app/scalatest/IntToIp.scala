package scalatest

import play.api.libs.json.{JsPath, Writes, Json}

import scala.collection.mutable.ListBuffer

/**
 * Created by zhangmeng on 16-11-16.
 */

case class Level(id: Int,name: String,parent: Int, subLevel: ListBuffer[Level] = new ListBuffer[Level]()) {
}
object Level {
  import play.api.libs.json._
  import play.api.libs.functional.syntax._
  implicit val writeLevel: Writes[Level] = (
    (__ \ "id").write[Int] and
    (__ \ "name").write[String] and
    (__ \ "parent").write[Int] and
    (__ \ "subLevel").lazyWrite(Writes.seq[Level](writeLevel))
  )(unlift(Level.unapply))
}



object IntToIp extends App {
  val levelOne = Level(1, "1", 0)
  val levelTwo = Level(2, "2", 0)
  val list = List(
    Level(1, "1", 0),
    Level(2, "2", 0),
    Level(3, "1-1", 1),
    Level(4, "1-2", 1),
    Level(5, "3", 0),
    Level(6, "2-1", 2),
    Level(7, "2-2", 2),
    Level(8, "3-1", 5),
    Level(9, "3-1-1", 8)
  )
  val parents = list.filter(e => e.parent == 0)

  for( l1 <- parents) { // 只支持到2级
    for(l2 <- list) {
      if( l2.parent == l1.id)  {
        l1.subLevel += (l2)
      }
    }
  }
  parents.foreach { p =>
    println(p.subLevel)
  }

  val tlist = List(Map("a" -> "b"), Map("a" ->"b"), Map("a" -> "c"))

  println(Json.toJson(tlist))

  println(Json.toJson(parents))
}
