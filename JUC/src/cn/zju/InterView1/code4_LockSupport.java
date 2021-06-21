package cn.zju.InterView1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class code4_LockSupport {
    List lists = new ArrayList();
    public void add(Object o) { lists.add(o); }
    public int size() { return lists.size(); }
    static Thread t1, t2;
    public static void main(String[] args) {
        code4_LockSupport c = new code4_LockSupport();
        t2 = new Thread(() -> {
            System.out.println("t2启动");
            LockSupport.park();
            System.out.println("t2结束");
            LockSupport.unpark(t1);
        });
        t2.start();
        t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t1.start();
    }
}
