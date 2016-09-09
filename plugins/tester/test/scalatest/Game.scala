package scalatest

/**
 * Created by zhangmeng on 16-7-26.
 */

case class Player(name: String, score: Int)
object Game extends App {
  def winner(p1: Player, p2: Player): Player = {
    if(p1.score > p2.score) p1 else p2
  }

  val players = List(Player("Sue", 7), Player("Bob", 8), Player("Joe", 4))

  val finalPlayer = players reduceLeft winner

  println(finalPlayer)

}

sealed trait MyList[+T]
case object Nil extends MyList[Nothing]
case class Cons[+T](h: T, t: MyList[T]) extends MyList[T]

object MyList {
  def sum(ints: MyList[Int]): Int = ints match {
    case Nil => 0
    case Cons(h, t) => h + sum(t)
  }
  def product(ds: MyList[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(h, t) => h * product(t)
  }
  def apply[T](xs: T*): MyList[T] =
    if(xs.isEmpty) Nil
    else Cons(xs.head, apply(xs.tail: _*))
}

object TT {
  def main(args: Array[String]): Unit = {
    val i: Int = 11;
    if(i == 10){
      println("return le")
      return
    }
    println("hello world")
  }
}
