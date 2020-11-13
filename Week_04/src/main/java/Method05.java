import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock Condition方式
 */
public class Method05 {
    private volatile Integer value = null;
    private Lock lock = new ReentrantLock();
    private Condition calComplete = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        final Method05 method = new Method05();
        Thread thread = new Thread(() -> {
            method.sum(36);
        });
        thread.start();

        int result = method.getValue();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    public void sum(int num) {
        lock.lock();
        try {
            value = fibo(num);
            calComplete.signal();
        } finally {
            lock.unlock();
        }
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() throws InterruptedException {
        lock.lock();
        try {
            while (value == null) {
                calComplete.await();
            }
        } finally {
            lock.unlock();
        }
        return value;
    }



}