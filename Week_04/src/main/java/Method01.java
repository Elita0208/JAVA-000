
import java.util.concurrent.CompletableFuture;

/**
 * 思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * CompletableFuture 方式
 */
public class Method01 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        int result = CompletableFuture.supplyAsync(()-> sum()).join();

        // 拿到result 并输出
        System.out.println("计算结果为：" + result);

        System.out.println("运行时间："+ (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}