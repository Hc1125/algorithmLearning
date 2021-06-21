package cn.zju.InterView3;

import java.util.concurrent.atomic.AtomicInteger;

public class code6_AtomicInteger {
    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (threadNo.get() != 1) {}
                System.out.print((i + 1) + " ");
                threadNo.set(2);
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (threadNo.get() != 2) {}
                System.out.print((char) ('A' + i) +" ");
                threadNo.set(1);
            }
        },"t2").start();
    }
}
