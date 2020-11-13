
/**
 * 无锁方式
 * 主要是通过不断循环查询返回值是否为空，来判断值是否已经计算完成
 */
public class Method06 {

    private volatile Integer value = null;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final Method06 method = new Method06();
        Thread thread = new Thread(() -> {
            method.sum(36);
        });
        thread.start();

        int result = method.getValue();

        System.out.println("计算结果为："+result);

        System.out.println("运行时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public void sum(int num) {
        value = fibo(num);
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() {
        while (value == null) {
            //do nothing
        }
        return value;
    }


}