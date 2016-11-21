package scalatest

/**
 * Created by zhangmeng on 16-11-16.
 */
object IntToIp {
  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  import WeekDay._
  def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)
  def main(args: Array[String]) = {
    WeekDay.values filter isWorkingDay foreach println
  }
}