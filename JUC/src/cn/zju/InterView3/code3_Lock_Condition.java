package cn.zju.InterView3;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class code3_Lock_Condition {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.print((i + 1) + " ");
                    condition.signalAll();
                    condition.await();
                }
                condition.signalAll();
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
                    condition.signalAll();
                    condition.await();
                }
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
