package cn.zju.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class threadTest7 {
    volatile List lists = new ArrayList();
    public void add(Object o){lists.add(o);}
    public int size(){ return lists.size();}

    public static void main(String[] args) {
        threadTest7 c = new threadTest7();
        CountDownLatch latch = new CountDownLatch(1);
        Thread t2 = new Thread(()->{
            System.out.println("t2 启动");
            if(c.size()!=5){
                LockSupport.park();
            }
            System.out.println("t2 结束");
        },"t2");
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+i);
                if(c.size()==5){
                    LockSupport.unpark(t2);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
    }
}
