package cn.zju.InterView3;

import java.util.concurrent.locks.LockSupport;

public class code2_LockSupport {
    static Thread t1, t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((i + 1) + " ");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park();
                System.out.print((char) ('A' + i) +" ");
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }
}
