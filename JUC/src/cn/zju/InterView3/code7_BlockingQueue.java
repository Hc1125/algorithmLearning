package cn.zju.InterView3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class code7_BlockingQueue {
    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((i + 1) + " ");
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print((char) ('A' + i) +" ");
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }
}
