package scalatest.date20160811

import java.io.{IOException, FileNotFoundException, FileReader}
import java.util.Date

import scala.io.Source
import scala.reflect.ClassTag

/**
 * Created by zhangmeng on 16-8-17.
 */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world !")
  }
}
package p {
  class Super {
    def f(): Unit = {
      println("f")
    }
  }
  class Sub extends Super {
    f()
  }
  class Other {
    (new Super).f()
  }
}
package bobsrocckets {
  package navigation {
    private[bobsrocckets] class Navigator {
      protected[navigation] def useStarChart() {}
      class LegOfJourney {
        private[Navigator] val distance = 100
      }
      private[this] var speed = 200
      new LegOfJourney().distance
    }
  }
  package launch {
    import navigation._
    object Vehicle {
      private[launch] val guide = new Navigator
    }
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val numList = List(1, 2, 3, 4, 5, 6)
    val newList = for(a <- numList if a != 3; if a < 5)  yield a
    newList foreach print
  }
}

object add {
  def addInt(a: Int, b: Int): Int = {
    val sum = a + b
    sum
  }
}

object XXTest {
  def main(args: Array[String]): Unit = {
    println("Returned Value: " + addInt(5, 7))
  }
  def addInt(a: Int, b: Int): Int = {
    val sum = a + b
    sum
  }
}

object Tet {
  def main(args: Array[String]): Unit = {
    delayed(time)
  }
  def time() = {
    println("获取时间，为纳秒")
    System.nanoTime
  }
  def delayed(t: => Long) = {
    println("在delayed方法内")
    println("参数: " + t)
    t
  }
}

object TetOne {
  def main(args: Array[String]): Unit = {
    printInt(b = 5, a = 7);
  }
  def printInt(a: Int, b: Int) = {
    println("Value of a: " + a)
    println("Value of b: " + b)
  }
}

object TetTwo {
  def main(args: Array[String]): Unit = {
    printStrings("Runoob", "Scala", "Python")
  }
  def printStrings(args: String* )  = {
    var i: Int = 0
    for(arg <- args) {
      println("Arg value[" + i + "] = " + arg)
      i = i + 1
    }
  }
}

object TetThree {
  def main(args: Array[String]): Unit = {
    for(i <- 1 to 10) {
      println(i + "　的阶乘: = " + facorial(i))
    }
  }
  def facorial(n: BigInt): BigInt = {
    if(n <= 1) 1
    else n * facorial(n -1)
  }
}

object TetFour {
  def main(args: Array[String]): Unit = {
    println(apply(layout, 10))
  }

  def apply(f: Int => String, v: Int) = f(v)
  def layout[A](x: A) = "[" + x.toString + "]"
}

object TetFive {
  def main(args: Array[String]): Unit = {
    def inc = (x: Int) => x + 1
    println(inc(7) - 1)
    val mul = (x: Int, y: Int) => x * y
    println(mul(3 ,4))
    val userDir = () => { System.getProperty("user.dir") }
    println(userDir())
  }
}

object TetSix {
  def main(args: Array[String]): Unit = {
    val date = new Date
    log(date, "message")
    Thread.sleep(1000)
    log(date, "message2")
    Thread.sleep(1000)
    log(date, "message3")
    val logWithDateBound = log(date, _: String)
    logWithDateBound("message4")
  }
  def log(date:Date, message: String) = {
    println(date + "----------" + message)
  }

  def addx(x: Int, y: Int) = x + y
  def addCur = (addx _).curried
}

object TetSev extends App{
  def strcat(s1: String)(s2: String) = {
    s1 + s2
  }
  val str1 = "Hello, "
  val str2 = "Scala!"
  println("str1 + str2 = " + strcat(str1)(str2))
}

object TetEig extends App {
  def multiplier(factor: Int) = (i: Int) => i * factor
  val multiplierOne = multiplier(1)
  val multiplierTen = multiplier(10)
  println("multiplier(1) value = " + multiplierOne(1))
  println("multiplier(2) value = " + multiplierTen(2))
}

object TetNin extends  App {
  val greeting: String = "Hello, world!"
  println(greeting)
}

object TetTen extends  App {
  val buf = new StringBuilder
  buf += 'a'
  buf ++= "bcdef"
}

object TetEle extends App {
  def hello(x: => String) = x
  Source.fromFile("zxcv")
}


object Tetwe extends  App {
  class Animal { val sound = "rustle" }
  class Bird extends Animal { override val sound = "call" }
  class Chicken extends Bird { override val sound = "cluck" }

  val getTweet: (Bird => String) = ((a: Animal) => a.sound)
  val hatch: (() => Bird) = (() => new Chicken)
}

class Triple[X, S, T](val first: X, val second: S, val third: T)
object TatOne extends App {
  val triple = new Triple("Spark", 3, 3.1415)
  val bigData = new Triple[String, String, Char]("Spark", "Hadoop", 'R')
  def getData[T](list: List[T]) = list(list.length / 2)
  println(getData(List("Spark", "Hadoop", 'R')))
  val f = getData[Int]_
  println(f(List(1, 2 , 3 , 4, 5 , 6, 7)))
}
object TatTwo extends App {
  class Pair[T <: Comparable[T]](val first: T, val second: T) {
    def bigger = if(first.compareTo(second) > 0) first else second
  }
  class Pair_Lower_Bound[T](val first: T, val second: T) {
    def replaceFirst[R >: T](newFirst: R) = new Pair_Lower_Bound[R](newFirst, second)
  }
}