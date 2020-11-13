
import java.util.concurrent.Semaphore;

/**
 * Semaphore方式
 */
public class Method07 {

    private volatile Integer value = null;
    final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        final Method07 method = new Method07();
        Thread thread = new Thread(() -> {
            try {
                method.sum(36);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        int result = method.getValue();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public void sum(int num) throws InterruptedException {
        semaphore.acquire();
        value = fibo(num);
        semaphore.release();
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() throws InterruptedException {
        int result = 0;
        semaphore.acquire();
        result = this.value;
        semaphore.release();
        return result;
    }



}