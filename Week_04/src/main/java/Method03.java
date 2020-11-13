import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierMethod 方式
 */
public class Method03 {

    private volatile Integer value = null;
    CyclicBarrier barrier;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        final Method03 method = new Method03();

        CyclicBarrier barrier = new CyclicBarrier(1, ()-> {
            int result = 0;
            result = method.getValue();

            System.out.println("计算结果为："+result);

            System.out.println("运行时间："+ (System.currentTimeMillis() - start) + " ms");
        });
        method.setBarrier(barrier);

        Thread thread = new Thread(() -> {
            try {
                method.sum(36);
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    private void sum(int num) throws BrokenBarrierException, InterruptedException {
        value = fibo(num);
        barrier.await();
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    private int getValue() {
        return value;
    }



}