
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread join 方式
 */
public class Method09 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        AtomicInteger value = new AtomicInteger();
        Thread thread = new Thread(()-> {
            value.set(fibo(36));
        });
        thread.start();
        thread.join();

        int result = value.get();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

}