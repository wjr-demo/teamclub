import akka.actor.{Props, ActorSystem, Actor}

/**
 * Created by zhangmeng on 16-6-29.
 */
class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _ => println("huh?")
  }
}
object Main extends App {
  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[HelloActor], name="helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}
