import com.avaje.ebean.Ebean;
import commons.support.counter.Counter;
import freemarker.template.SimpleDate;
import models.AppDomain;
import models.AppFuncTree;
import org.junit.Test;
import play.cache.Cache;
import play.libs.Crypto;
import play.libs.Json;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangmeng on 16-6-27.
 */
public class MemcacheTest extends BaseTest {
    @Test
    public void testMemcache(){
        System.out.println(Crypto.encryptAES("912ec803b2ce49e4a541068d495ab570"));
    }

    @Test
    public void testEbean() {
        List<AppFuncTree> lists = AppFuncTree.finder().all();
        System.out.println(AppFuncTree.toJson(lists));
    }

    @Test
    public void testEncrypt() {
        String s = Crypto.encryptAES("1234|2345|3456");
        String[] ss = Crypto.decryptAES(s).split("\\|");
        for(String single : ss) {
            System.out.println(single);
        }
    }

    @Test
    public void testCounter() throws Exception {
        final List<String> queues = Collections.synchronizedList(new ArrayList());
        final CountDownLatch latch = new CountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        final AtomicInteger ai = new AtomicInteger(0);
        for(int i = 0 ; i < 5; i++) {
            ai.incrementAndGet();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    latch.countDown();
                    Counter counter = Counter.create("hello");
                    Integer s = counter.incr();
                    try {
                        Thread.sleep(ai.get() * 100);
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    queues.add(String.valueOf(s));
                }
            });
        }
        latch.await();

        Thread.sleep(5000);

        Object[] objects = queues.toArray();
        System.out.println("size: " + objects.length);
    }
}
