package xconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangmeng on 16-10-28.
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();  //不满
    final Condition notEmpty = lock.newCondition(); //不空

    final Object[] items = new Object[100];

    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while(count == items.length)
                notFull.await();
            items[putptr] = x;
            if(++putptr == items.length) putptr = 0;
            ++count ;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if(++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
