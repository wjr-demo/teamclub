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


object TraitSelf {
  def main(args: Array[String]): Unit = {
    BX.printXX()
  }

}
