package scalatest.date20160811.subpackage

import scala.util.Try

/**
 * Created by zhangmeng on 16-8-19.
 */

trait Cache {
  def itemIdToShopId: Int => Int
  def cache = scala.collection.mutable.Map.empty[Int, Int]
  def cachedItemIdToShopId(itemId: Int): Int = {
    cache.get(itemId) match {
      case Some(shopId) => shopId
      case None =>
        val shopId = itemIdToShopId(itemId)
        cache += itemId -> shopId
        shopId
    }
  }
  val (one, two) = (1, 2)
  def sellerAndItemId(orderId: Int): (Int, Int) = orderId match {
    case 0 => (1, 2)
  }

  val sampleList = List((1, 2, 3), (4, 5, 6), (7, 8, 9))
  sampleList map {
    case (orderId, shopId, itemId) =>
      println(s"${orderId}_${shopId}_${itemId}")
  }
}
case class Sample(a: String, b: String, c: String, d: String, e: String)

object XApp extends App {
//  def showContent(x: Any) = x  match {
//    case Sample(a, b, c, d, e) => $"Sample $a, $b $c"
//    case (a, b, d,e) => ???
//    case head :: second :: test =>
//  }
//  val sampleList = List((1, 2, 3), (4, 5, 6), (7, 8, 9))
//  sampleList map {
//    case (orderId, shopId, itemId) =>
//      println(s"${orderId}_${shopId}_${itemId}")
//  }
//  def descibe(x: Any) = x match {
//    case 5 => "five"
//    case true => "truth"
//    case "hello" => "hi!"
//  }
//
  val a: Option[String] = Some("1024")
//  val b: Option[String] = None
//  a.map(_.toInt)
//  b.map(_.toInt)
//  a.filter(_ == "2048")
//  b.filter(_ == "2048")
//  a.getOrElse("2048")
//  b.getOrElse("2048")
  val resp = a.map(_.toInt).map(_ + 1).map(_ / 5).map(_ / 2 == 0)
  val b: Seq[String] = Seq("1", "2", "3", null, "4")
  val c: Seq[Option[String]] = Seq(Some("1"), Some("2"), Some("3"), None, Some("4"))
  println(c.flatMap(_.map(_.toInt)))
  b.filter(_ != null).map(_.toInt)

  Seq(1, 2, 3).headOption

  Seq("a", "b", "c", null, "d").map(Option(_))

  try{
    1024 / 0
  }catch {
    case e: Throwable => e.printStackTrace()
  }

  val seq = Seq(0, 1, 2, 3, 4)
  val seqTry = seq.map(x => Try{ 20 / x})
  seqTry flatMap (_.toOption)
}