package cn.zju.InterView1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class code5_Semaphore {
    List lists = new ArrayList();
    public void add(Object o) { lists.add(o); }
    public int size() { return lists.size(); }
    static Thread t2;
    public static void main(String[] args) {
        code5_Semaphore c = new code5_Semaphore();
        Semaphore s1 = new Semaphore(5);
        Semaphore s2 = new Semaphore(0);
        new Thread(() -> {
            System.out.println("t2启动");
            try {
                s2.acquire(5);
                System.out.println("t2结束");
                s1.release(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "t2").start();
        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                try {
                    s1.acquire(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    s2.release(5);
                }
            }
        }, "t1").start();
    }
}
