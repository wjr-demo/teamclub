package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import net.spy.memcached.compat.log.LoggerFactory;
import play.Logger;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-12-12.
 */
public class System {
    public static void main(String[] args) throws Exception{
        final ActorSystem actorSystem = ActorSystem.create("actor-system");
        final ActorRef testActor = actorSystem.actorOf(Props.create(TestActor.class));

        actorSystem.scheduler().scheduleOnce(Duration.create(2000, TimeUnit.MILLISECONDS), new Runnable() {
            @Override
            public void run() {
                testActor.tell("haha", null);
            }
        }, actorSystem.dispatcher());

        Thread.sleep(5000);

        actorSystem.shutdown();
    }
}
