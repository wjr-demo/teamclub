package scalatest

/**
 * Created by zhangmeng on 16-11-16.
 */
object IntToIp extends App {
  val is = List(1, 2, 3, 4 ,5 ,6)
  val resp = is find (_ % 2 == 0)
  println(resp)
}
