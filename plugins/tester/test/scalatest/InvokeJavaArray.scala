package scalatest

import javatest.ObjectArrayT

import play.libs.Scala

/**
 * Created by zhangmeng on 16-8-23.
 */
object InvokeJavaArray {
  def getAny(anys: Array[Object]): Unit = {
    getAny(anys: _*)
  }

  def getAny(anys: Any*): Unit = {
    val arrays = for(any <- anys) yield any.asInstanceOf[AnyRef]
    ObjectArrayT.show(arrays.toArray)
  }

  def main(args: Array[String]): Unit = {
    val xxs: java.util.List[Any] = new java.util.ArrayList[Any]
//    xxs.add("asdf")
//    xxs.add("cvsd")
    getAny(xxs)
  }
}
