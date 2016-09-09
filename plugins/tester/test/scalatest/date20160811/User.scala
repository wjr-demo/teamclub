package scalatest.date20160811

/**
 * Created by zhangmeng on 16-8-11.
 */
trait User {
  def name: String
}

class FreeUser(val name: String) extends User {
  var score: Int = _
  def c = ???
  def this(name: String, score: Int) {
    this(name)
    this.score = score
  }
}

object scoreUnapply {
  def unapply(user: FreeUser): Boolean = user.score > 10
}

object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int)] = Some((user.name, user.score))

  def main(args: Array[String]): Unit = {
    val user: User = new FreeUser("haha", 11)
    user match {
      case freeUser @ scoreUnapply() => println(freeUser.name)
      case FreeUser(name, age) => println("Hello " + name + ", age " + age)
      case _ => ???
    }
  }
}
