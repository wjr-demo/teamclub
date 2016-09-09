import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by zhangmeng on 16-6-28.
 */
public class ParkTest {
    public static void main(String[] args){
        final Thread t = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(Exception e){
                    e.printStackTrace();
                }
                LockSupport.unpark(t);
            }
        }).start();
        LockSupport.park();
        System.out.println("block..");
    }
}
