package cn.zju.Synchronized;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 修饰静态方法，是对类进行加锁，如果该类中有methodA 和methodB都是被synchronized修饰的静态
 * 方法，此时有两个线程T1、T2分别调用methodA()和methodB()，则T2会阻塞等待直到T1执行完成之后
 * 才能执行。
 * 修饰实例方法时，是对实例进行加锁，锁的是实例对象的对象头，如果调用同一个对象的两个不同的被
 * synchronized修饰的实例方法时，看到的效果和上面的一样，如果调用不同对象的两个不同的被synchronized修饰的实例方法时，则不会阻塞。
 */
public class code3 {
    public static class Test {
        synchronized public static void A() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A----------");
        }
        synchronized public static void D() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("D----------");
        }
        synchronized public void B() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B----------");
        }
        synchronized public void E() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("E----------");
        }

        public void C() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C----------");
        }
    }

    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test();
        new Thread(() -> {
            t1.B();
        }).start();
        new Thread(() -> {
            t1.E();
        }).start();
    }
}
