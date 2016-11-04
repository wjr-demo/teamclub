package xconcurrent;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangmeng on 16-10-28.
 */
public class TnterruptTest {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("Lock it");
        }finally {
            lock.unlock();
        }
    }

}
