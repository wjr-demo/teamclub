package scalatest

/**
 * Created by zhangmeng on 16-11-7.
 */
object PartialFuncTest extends App {
  val sample = 1 to 10
  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }

  val evenNumbers = sample collect isEven

  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 => x + " is odd"
  }

  val numbers = sample map ( isEven orElse isOdd)

}
