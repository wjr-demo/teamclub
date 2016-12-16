package actor;

import akka.actor.UntypedActor;

/**
 * Created by zhangmeng on 16-12-12.
 */
public class TestActor extends UntypedActor {
    public TestActor() {}

    @Override
    public void onReceive(Object message) throws Exception {
        java.lang.System.out.println("msg: " + message);
    }
}
