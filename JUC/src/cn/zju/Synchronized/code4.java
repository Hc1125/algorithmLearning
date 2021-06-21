package cn.zju.Synchronized;

/**
 * synchronized修饰类会对这个类的所有实例对象的所有方法都起作用
 */
public class code4 {
    public static class Test {
        public void A() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A----------");
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
            synchronized (Test.class) {
                t1.A();
            }
        }).start();
        new Thread(() -> {
            synchronized (Test.class) {
                t2.A();
            }
        }).start();
    }
}
