package cn.zju.threadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadTest1 {
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread1");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread2");
        }
    });
    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread3");
        }
    });
    //控制多线程执行顺序
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws InterruptedException {
        //方法1
        /*thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();*/
        //方法2
        /*executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.shutdown();*/

    }
}
