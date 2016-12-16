package filegene

import java.io.{File, PrintWriter}
import java.util.UUID

/**
 * Created by zhangmeng on 16-11-10.
 */

trait Person
trait PlayGame {}
trait ReadBook {}

class Student extends Person with PlayGame with ReadBook

class LittleBoy extends Person with PlayGame

case class Address(stree: String, city: String, country: String)



object FileGenerator {

  val b = true

  b match {
    case true => println("true")
    case false => println("false")
  }


  def geneOneLine(): String = {
    val listStr = for(i <- 1 to 10) yield UUID.randomUUID.toString
    listStr.mkString(",")
  }

  def write100000(): Unit = {
    println(geneOneLine())
    val pw = new PrintWriter(new File("sql-import.data"))
    for(i <- 1 to 1000000) {
      println(s"current %d".format(i))
      pw.println(geneOneLine())
    }
    pw.close
  }

  def geneThousandFile(): Unit = {

  }

  def main(args: Array[String]) = {
    write100000
  }
}
