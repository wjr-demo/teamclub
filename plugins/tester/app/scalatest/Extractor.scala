package scalatest

/**
 * Created by zhangmeng on 16-7-14.
 */

object Extractor {
  case class User(firstName: String, lastName: String, score: Int)
  def advance(xs: List[User]) = xs match {
    case User(_, _, score1) :: User(_, _ , score2) :: _ => score1 - score2
    case _ => 0
  }
}

trait User {
  def name: String
}
class FreeUser(val name: String) extends User
class PremiumUser(val name: String) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}



object AA extends App{
  val numbers = List(1, 2, 3, 4)
  println(numbers.map((i: Int) => i * 2))
  println(List(1, 2, 3).zip(List("a", "b", "c")))
  numbers.partition(_ % 2 == 0)
  numbers.foldLeft(0)((m: Int, n: Int) => m + n)

  println(List(List(1, 2), List(3, 4)).flatten)

  List(List(1, 2), List(3, 4)).flatMap(x => x.map(_ * 2))

  val zextensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 101)

  val nestedNumbers = List(List(1, 2), List(3, 4))
  val resp = nestedNumbers flatMap (x => x.map(_ * 2))
  println(resp)

  case class PhoneExt(name: String, ext: Int)
  val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
  extensions.filter { case PhoneExt(name, extension) => extension < 200 }

  val list = List(Some(5), None, Some(6))
  val list_resp = list map {
    case Some(x) => x
    case None => 0
  }
  println(list_resp)

  val chars = 'a' to 'z'
  val perms = for {
    a <- chars
    b <- chars
    if a != b
  } yield "%c%c".format(a, b)
  println(perms)
}