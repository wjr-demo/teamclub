import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit

import play.api.DefaultApplication
import play.api.libs.iteratee._
import play.api.mvc.RequestHeader
import play.core.Router.Routes
import play.core.StaticApplication
import play.libs.F.Promise
import play.test.Helpers

import scala.concurrent.Future
import scala.util.{Random, Failure, Success}

/**
 * Created by zhangmeng on 16-6-28.
 */
object IterateeTest  {
  val l = List(1, 234, 455, 987)
  def methodOne = {
    var total = 0
    var it = l.iterator
    while(it.hasNext) {
      total += it.next
    }
    println(total)
  }

  def methodTwo = {
    var total = 0
    for( item <- l) {
      total += item
    }
    println(total)
  }

  def methodThree = {
    var total = 0
    l.foreach {
      item => total += item
    }
    println(total)
  }

  val l2 = List(134, 664, 987, 456)

  def methodFour = {
    var total = 0
    def step(item: Int) = total += item
    l foreach step
    println(total)
  }

  def methodFive = {
    def foreach(l: List[Int]) = {
      def step(l: List[Int], total: Int): Int = {
        l match {
          case List() => {
            total
          }
          case head :: tail => {
            step(tail, total + head)
          }
        }
      }
      println(step(l, 0))
    }
    foreach(l)
  }

  def methodSix = {
    val resp = l.foldLeft(0) {
      (total, elt) => total + elt
    }
    println(resp)
  }

  import scala.concurrent.ExecutionContext.Implicits.global
  def testOne = {
    val enumerator = Enumerator(1, 234, 455, 987)
    val resp = enumerator.run(Iteratee.fold(0) {
      (total, elt) => total + elt
    })
    resp onComplete  {
      case Success(s) => println(s)
      case Failure(_) =>
    }
  }

  def testTwo = {
    val dateGenerator: Enumerator[String] = Enumerator.generateM(
      play.api.libs.concurrent.Promise.timeout({
          println("")
          Some("curren time %s".format(new Date()))
        },
        500
      )
    )
  }

  def testThree = {
    def total10Chunks: Iteratee[Int, Int] = {
      def step(idx: Int, total: Int)(i: Input[Int]): Iteratee[Int, Int] = i match {
        case Input.EOF | Input.Empty => Done(total, Input.EOF)
        case Input.El(e) =>
          if(idx < 10) Cont[Int, Int]{
            i => step(idx + 1, total + e)(i)
          }
          else Done(total, Input.EOF)
      }
      (Cont[Int,Int](i => step(0, 0)(i)))
    }
    val dateGenerator: Enumerator[String] = Enumerator.generateM(
      Future {
        TimeUnit.MILLISECONDS.sleep(300)
        Option(String.valueOf(Random.nextInt(10)))
      }
    )
    val promiseTotal = Enumerator(10, 20 , 5) run total10Chunks

    promiseTotal.map(println _)

    val dateGene = dateGenerator through Enumeratee.map(_.toInt) run total10Chunks

    dateGene.map(println _)

  }

  def testFour = {
    List[(String,Routes)]().foreach {
      case (p, router) => {
        println(p)
      }
    }
  }

  def getPrefixByRequest(uri: String): Option[String] = {
    val matcher = """^/(.+)/.+""".r
    uri match {
      case matcher(prefix) => Some(prefix)
      case _ => None
    }
  }

  def main(args: Array[String]): Unit = {
//    methodOne
//    methodTwo
//    methodThree
//    methodFour
//    methodFive
//    testOne
//    testThree
//    testFour
    val prefix = "/hello/zxcvasd"
    val op = getPrefixByRequest(prefix)
    println(op getOrElse "asd")

    TimeUnit.SECONDS.sleep(2)
  }

}
