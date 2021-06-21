package cn.zju.InterView1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class code3_CountDownLatch {
    List lists = new ArrayList();
    public void add(Object o) { lists.add(o); }
    public int size() { return lists.size(); }

    public static void main(String[] args) {
        code3_CountDownLatch c = new code3_CountDownLatch();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2启动");
            if (c.size() != 5) {
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
            latch2.countDown();
        }, "t2").start();
        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
