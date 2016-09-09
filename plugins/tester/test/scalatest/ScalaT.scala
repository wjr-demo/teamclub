package scalatest

import java.util

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.Module.SetupContext
import com.fasterxml.jackson.databind.ser.Serializers
import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.databind.deser.Deserializers
import com.fasterxml.jackson.databind.module.SimpleModule
import commons.{Eithers, ErrorCode}
import org.apache.bcel.classfile.InnerClass
import play.api.libs.json.{JsValue, JsNull, Json}
import play.libs.Scala

import scala.util.{Failure, Success, Try}

/**
 * Created by zhangmeng on 16-8-10.
 */
object TTT {
  def exprToInt(expr: String): Int => Boolean = {
    val Array(mark, num, _*) = expr.split(" ")
    val numInt = num.toInt
    mark match {
      case "<" => numInt.>
      case ">" => numInt.<
      case ">=" => numInt.<=
      case "<=" => numInt.>=
    }
  }
  case class RangeMatcher(range: Seq[String]) {
    val rangeFunc: Seq[(Int) => Boolean] = range.map(exprToInt)
    def check(input: Int) = rangeFunc.forall(_(input))
  }

  def main(args: Array[String]): Unit = {
    val requirements = Seq(">= 3", "< 7")
    val rangeMatcher = RangeMatcher(requirements)
    val results = Seq(1, 3, 5, 7, 9) map (rangeMatcher.check)
    println(results.mkString(","))
  }
}

trait User {
  def name: String
}
trait B {
  self =>
  def foo(): Unit = {
    println("asdf")
  }
}

object XMain extends App {
  val i: java.lang.Integer = -1
  println(Int.unbox(i))
}