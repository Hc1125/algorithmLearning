package cn.zju.ScheduledThreadPoolExecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) {
        /**
         * ScheduledThreadPoolExecutor当核心线程数过少，线程会在执行上一个任务中耗时阻塞住
         * 导致后续延时任务不能有效准时执行，会被延时执行
         */
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.schedule(() -> {
            System.out.println(1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
        executor.schedule(() -> {
            System.out.println(1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
        executor.schedule(() -> {
            System.out.println(1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
        executor.schedule(() -> {
            System.out.println(1);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
    }
}
