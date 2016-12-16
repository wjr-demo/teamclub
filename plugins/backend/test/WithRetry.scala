/**
 * Created by zhangmeng on 16-6-27.
 */

import java.net.URL

import Retry._

import concurrent.{Future, Promise}
import concurrent.future
import scala.util.{Try, Failure, Success}

case class Customer(age: Int)
class Cigarettes
case class UnderAgeException(message: String) extends Exception(message)


object EitherT extends App {
  import scala.io.Source
  import java.net.URL
  def getContent(url: URL): Either[String, Source] =
    if(url.getHost.contains("google")) Left("Requested URL is bloacked for the good of the people!")
    else Right(Source.fromURL(url))
  println(getContent(new URL("https://plus.google.com")).right.map(_.getLines))
}


object HandlerTry {
  def parseURL(url: String): Try[URL] = Try(new URL(url))

  def buyCigarettes(customer: Customer): Cigarettes =
    if(customer.age < 16)
      throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
    else new Cigarettes

  def main(args: Array[String]) = {
    val youngCustomer = Customer(17)
    val s = try {
      buyCigarettes(youngCustomer)
      "Yo, here are your cancer sticks ! Happy smokin"
    } catch {
      case UnderAgeException(msg) => msg
    }
    println(s)

    val url = parseURL(Console.readLine("URL: ")) getOrElse new URL("http://www.baidu.com")
    println(url)

    val protocol = parseURL("https://danielwestheide.com").map(_.getProtocol)
    println(protocol)
  }
}
