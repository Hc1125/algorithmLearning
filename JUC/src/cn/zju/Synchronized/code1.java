package cn.zju.Synchronized;



public class code1 {
    public static class A {
        public static synchronized void in() {
            System.out.println("in");
        }
        public static synchronized void out() {
            System.out.println("out");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A b = new A();
        new Thread(() -> {
            synchronized (A.class) {
                a.in();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.out();
        }).start();
    }
}
