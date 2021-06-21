package cn.zju.JUC;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class code2_BoundedBuffer {
    final Lock lock = new ReentrantLock();          // 重入锁对象
    final Condition notFull = lock.newCondition();  // 队列满条件变量
    final Condition notEmpty = lock.newCondition(); // 队列空条件变量

    final Object[] items = new Object[100];  //100容量的buffer
    int putptr, takeptr, count;  // put下标， take下标，count为队列中有效item数量

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();          // 将线程阻塞在队列满的条件变量上
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0; //超出buffer的大小，重置put指针
            ++count;
            notEmpty.signal();            // 唤醒等待在队列空的条件变量上的线程
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)           // 队列为空，那么等待在队列空的条件变量上
                notEmpty.await();
            Object x = items[takeptr];   // 获取take指针出的item
            if (++takeptr == items.length) takeptr = 0; // 超出大小，重置take指针
            --count;
            notFull.signal();            //唤醒等待在队列满的条件变量上的线程
            return x;
        } finally {
            lock.unlock();
        }
    }
}
