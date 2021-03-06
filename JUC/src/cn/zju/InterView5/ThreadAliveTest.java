package cn.zju.InterView5;

/**
 * 为什么线程结束了，isAlive方法还返回true
 */

/**
 * 这下我们就可以通过以上知识来分析为何isAlive方法在线程执行完毕后仍然返回true了，这是由于isAlive方法通过判断Java线程对象的eetop变量来判定线程是否存活，
 * 而当我们线程执行完毕后将会调用exit方法，该方法将会调用ensure_join方法，在该方法中将eetop设置为null，
 * 但是由于赋值前需要获取到Java线程的对象锁，而该对象的对象锁已经由线程T2持有，这时当前线程将会阻塞，从而造成eetop变量没有被清除，
 * 从而导致isAlive方法在T1线程执行完毕后仍然返回true。
 * 读者也可以看看java Thread的源码，join函数也是通过对Thread对象获取锁然后调用isAlive来判定线程是否结束的，
 * 这就意味着如果我们用别的线程持有了Java Thread的对象锁，那么这时调用join方法的线程也是会被阻塞的。
 */
public class ThreadAliveTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (t1) {
                System.out.println("t2 start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 isAlive:" + t1.isAlive());
            }
        });
        t2.start();
    }
}
