package scalatest

/**
 * Created by zhangmeng on 16-7-26.
 */
object TList extends App {
  val list = List(1, 4, 9)
  println(list.aggregate(0: Int)(_ + _% 8, _ + _))

  def add2(num: Int) = num + 2
  def print(num: Int) = println(num)
  val addAndPrint = add2 _ andThen println _
  addAndPrint(2)

  val listCollect = list collect {
    case item if item % 2 == 1 => item * 2
  }
  println(listCollect)

  val listCollectFirst = list collectFirst {
    case item if item % 2 == 1 => item * 2
  } getOrElse 0
  println(listCollectFirst)

  def add3(num: Int) = num + 3
  val add5 = add2 _ compose add3 _
  println(add5(1))

  val list2 = List(2, 8, 18)
  list.corresponds(list2) { _ * 2 == _ }

  println(list.count(_ % 2 == 0))

  println(list.dropWhile(_ < 8))

  println(list.exists(_ % 2 == 0))

  println(list.filter(_ %2 == 0))

  println(list.filterNot(_ % 2 == 0))

  println(list.find(_ % 2 == 0))

  val list3 = List(List(2, 3), List(4, 5))
  println(list3 flatten)

  println((1 to 10).fold(1)(_ * _))

  println(list.forall(_ % 2 == 0))

  list.foreach(println)

  println(list groupBy ( _ % 8 ))

  println(list.indexWhere(_ % 2 == 0))

  println(list.map(_ * 2))

  println(list.maxBy(_ % 7))

  println(list.minBy(_ % 7))

  println(list.partition(_ % 2 == 0))

  println(list.prefixLength(_ < 8))

  println(list.reduce(_ + _))

  println(list.reduceOption(_ * _))

  println(list.reverseMap(_ * 2))

  val pf:PartialFunction[Int, Int] = {case m: Int if m % 2 == 1 => m * 2}
  pf.runWith(println)(3)

  (1 to 10).scan(1)(_ * _)

  list.sortWith(_ > _)
}
