package akkatest;

import akka.actor.*;
import scala.concurrent.duration.Duration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-8-22.
 */
public class HelloWorldAkka {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloakka");

        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");

        final Inbox inbox = Inbox.create(system);

        greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());

        inbox.send(greeter, new Greet());

        Greeting greeting1 = (Greeting)inbox.receive(Duration.create(5, TimeUnit.SECONDS));

        System.out.println("Greeting: " + greeting1.message);

        greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());

        inbox.send(greeter, new Greet());

        Greeting greeting2 = (Greeting)inbox.receive(Duration.create(5, TimeUnit.SECONDS));

        System.out.println("Greeting: " + greeting2.message);

        ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));

        system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter);

//        system.shutdown();
    }
}

//打招呼动作
class Greet implements Serializable {
}

//打招呼的内容
class Greeting implements Serializable {
    public final String message;
    public Greeting(String message) {
        this.message = message;
    }
}

//打招呼的人
class WhoToGreet implements Serializable {
    public final String who;
    public WhoToGreet(String who) {
        this.who = who;
    }
}

class Greeter extends UntypedActor {
    String greeting = "";

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof WhoToGreet) {
            greeting += "Hello, " + ((WhoToGreet)message).who;
        }else if(message instanceof Greet) {
            getSender().tell(new Greeting(greeting), getSelf());
        }else {
            unhandled(message);
        }
    }
}

class GreetPrinter extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Greeting) {
            System.out.println(((Greeting) message).message);
        }
    }
}