import org.springframework.beans.BeanUtils

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-6-30.
 */
object CX {
  def name = "hello"
  trait AX {
    self =>
    val xx = "hello"
    def printXX(): Unit ={
      println(name)
    }
  }
}

object BX extends CX.AX {
  override val xx = "world"
}

class A {
  @BeanProperty
  var x: String = _
  @BeanProperty
  var y: Int = _
}

class B {
  @BeanProperty
  var x: String = _
  @BeanProperty
  var y: Int = _
}

object TraitSelf {
  def main(args: Array[String]): Unit = {
    val a = new A
    a.x = "hello"
    a.y = 1234
    var b = new B
    BeanUtils.copyProperties(a, b)
    println(b.x)
    println(b.y)
  }

}
