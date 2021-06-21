package cn.zju.InterView3;

public class code1_sync_wait_notify {
    /**
     * 要求用线程顺序打印A1B2C3....Z26
     */
    public static void main(String[] args) {
        final Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.print((i + 1) + " ");
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }," t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i) +" ");
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }," t2").start();
    }
}
