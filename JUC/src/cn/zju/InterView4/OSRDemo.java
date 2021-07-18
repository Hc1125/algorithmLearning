package cn.zju.InterView4;

public class OSRDemo {
    /**
     * c2编译器bug，认为问题线程是有限循环
     * 将i从int改成long，编译器则认为是无限循环
     * 或者在method里添加复杂代码逻辑
     * 或者在JVM参数设置里设置分层编译等级
     * 或者直接给couter添加volatile不允许编译器优化
     */
    static long counter;

    public static void main(String[] args) throws Exception{
        System.out.println("main start");
        startBusinessThread();
        startProblemThread();
        // 等待线程启动执行
        Thread.sleep(500);
        // 执行GC
        System.gc();
        System.out.println("main end");
    }

    public static void startProblemThread() { new Thread(new MyRun()).start(); }

    public static class MyRun implements Runnable {

        @Override
        public void run() {
            System.out.println("Problem start");
            for (int i = 0; i < 1000000000; i++) {
                for (int j = 0; j < 1000; j++) {
                    counter += i % 33;
                    counter += i % 333;
                }
                // method();
            }
            System.out.println("Problem end");
        }
    }

    public static void method() {

    }

    public static void startBusinessThread() {
        new Thread(() -> {
            System.out.println("业务线程-1 start");
            for (; ; ) {
                System.out.println("执行业务1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("业务线程-2 start");
            for (; ; ) {
                System.out.println("执行业务2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
