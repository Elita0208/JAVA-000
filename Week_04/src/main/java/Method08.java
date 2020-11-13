
/**
 * synchronized方式
 */
public class Method08 {

    private volatile Integer value = null;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        final Method08 method = new Method08();
        Thread thread = new Thread(() -> {
            method.sum(36);
        });
        thread.start();

        int result = method.getValue();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    synchronized public void sum(int num) {
        value = fibo(num);
        notifyAll();
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    synchronized public int getValue() throws InterruptedException {
        while (value == null) {
            wait();
        }
        return value;
    }



}