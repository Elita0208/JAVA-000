import java.util.concurrent.*;

/**
 * Future
 */
public class Method04 implements Callable<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Long> future = executor.submit(new Method04());

        long result = future.get();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");

        executor.shutdown();
    }

    @Override
    public Long call() {
        return fibo(40);
    }

    private long fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

}