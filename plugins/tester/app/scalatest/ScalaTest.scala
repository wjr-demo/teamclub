package scalatest

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Created by zhangmeng on 16-7-14.
 */
object ScalaTest {
  val routeMap = scala.collection.mutable.Map[String, String]()
  val routM = new mutable.HashMap[String, String] with mutable.SynchronizedMap[String, String]
  val seq  = new ListBuffer[String]()


  import scala.util.control.Exception._

  def main(args: Array[String]): Unit = {
    val resp = allCatch[Void].opt("42a".toInt)
    println(resp)
  }
}
