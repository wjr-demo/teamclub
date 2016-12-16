package aoptest.spring;

import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangmeng on 16-12-12.
 */
public class TimeOutTest {
    public static void main(String[] args) throws Exception {
//        final Timer timer = new HashedWheelTimer();
//        Thread thread = new Thread(new Runnable() {
//            int i = 0 ;
//            @Override
//            public void run() {
//                try{
//                    while(true) {
//                        System.out.println("i: " + (i++));
//                        Thread.sleep(1000);
//                    }
//                }catch(Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println("HELLO WORLD!");
//            }
//        });
//        thread.start();
//        timer.newTimeout(new TimerTask() {
//            @Override
//            public void run(Timeout timeout) throws Exception {
//                System.out.println("timeout 5");
//            }
//        }, 5, TimeUnit.SECONDS);
//        timer.newTimeout(new TimerTask() {
//            @Override
//            public void run(Timeout timeout) throws Exception {
//                System.out.println("timeout 10");
//            }
//        }, 10, TimeUnit.SECONDS);
    }
}
