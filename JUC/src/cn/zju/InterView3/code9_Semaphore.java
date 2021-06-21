package cn.zju.InterView3;

import java.util.concurrent.Semaphore;

public class code9_Semaphore {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print((i + 1) + " ");
                s2.release();
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print((char) ('A' + i) +" ");
                s1.release();
            }
        },"t2").start();
    }
}
