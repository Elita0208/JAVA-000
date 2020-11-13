import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatchMethod 方式
 */
public class Method02 {

    private volatile Integer value = null;
    private CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(1);
        final Method02 method = new Method02();
        method.latch = latch;
        Thread thread = new Thread(() -> {
            method.sum(36);
        });
        thread.start();

        int result = method.getValue(); //这是得到的返回值

        //拿到result 并输出
        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public void sum(int num) {
        value = fibo(num);
        latch.countDown();
    }

    public int getValue() throws InterruptedException {
        latch.await();
        return value;
    }





}