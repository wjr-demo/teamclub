package scalatest.date20160811

/**
 * Created by zhangmeng on 16-8-18.
 */
class XX {}
class Animal{}
class Bird extends Animal {}
class Consumer[-S, +T]()(implicit m1: Manifest[T]) {
  def get(): T = {m1.runtimeClass.newInstance.asInstanceOf[T]}
  def get[U <: S]()(implicit m2:Manifest[U]): U = {m2.runtimeClass.newInstance.asInstanceOf[U]}
}
object TTest extends App {
  val c: Consumer[Bird, Animal] = new Consumer[Animal, Bird]()
//  c.m1(new XX)
}