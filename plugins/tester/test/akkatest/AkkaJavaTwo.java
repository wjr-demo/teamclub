package akkatest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import akka.routing.Router;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 16-8-22.
 */
public class AkkaJavaTwo {
    public static void main(String[] args) throws Exception{
        URL url = AkkaJavaTwo.class.getClassLoader().getResource("akkatest/demo5.conf");
        String contents =  Resources.toString(url, Charsets.UTF_8);
        Config demo5 = ConfigFactory.parseString(contents).getConfig("demo5");
        final ActorSystem system = ActorSystem.create("demo5", demo5);
        final ActorRef controlActor = system.actorOf(Props.create(ControlActor.class), "control");
        controlActor.tell(new StartCommand(100), ActorRef.noSender());
    }
}

class StartCommand implements Serializable {
    private int actorCount = 0;
    public StartCommand() {

    }
    public StartCommand(int actorCount) {
        this.actorCount = actorCount;
    }
    public int getActorCount(){
        return actorCount;
    }
    public void setActorCount(int actorCount) {
        this.actorCount = actorCount;
    }
}

class WriterActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println(Thread.currentThread().getName());
    }
}

class ControlActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof StartCommand) {
            Props props = Props.create(WriterActor.class).withDispatcher("writer-dispatcher").withRouter(new RoundRobinRouter(5));
            ActorRef writer = getContext().actorOf(props, "writer");
            writer.tell("Insert", ActorRef.noSender());
        }
    }
}