package cn.zju.InterView3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class code4_Lock_Condition {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.print((i + 1) + " ");
                    conditionT2.signal();
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i) +" ");
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
