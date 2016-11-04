package xconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangmeng on 16-10-28.
 */
class Depot {
    private int capacity;
    private int size ;
    private Lock lock ;
    private Condition fullCondition;
    private Condition emptyCondition;
    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0 ;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            int left = val;
            while(left > 0) {
                while(size >= capacity) {
                    fullCondition.await();
                }
                int inc = (size + left) > capacity ? (capacity - size) : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
                emptyCondition.signal();
            }
        } catch(InterruptedException e){
        }finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            int left = val;
            while(left > 0) {
                while(size <= 0)
                    emptyCondition.await();
                int dec = (size < left) ? size : left ;
                size -= dec;
                left -= dec;
                System.out.printf("%s counsume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
        }
        finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity: " + capacity + ", actual size: " + size ;
    }
}

class Producer {
    private Depot depot;
    public Producer(Depot depot) {
        this.depot = depot;
    }
    public void produce(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}

class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }
    public void consume(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}

public class LockTest1 {
    public static void main(String[] args) {
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}