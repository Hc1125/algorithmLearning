package cn.zju.InterView1;

import java.util.ArrayList;
import java.util.List;

public class code2_NotifyHoldingLock {
    List lists = new ArrayList();
    public void add(Object o) { lists.add(o); }
    public int size() { return lists.size(); }

    public static void main(String[] args) {
        code2_NotifyHoldingLock c = new code2_NotifyHoldingLock();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}
