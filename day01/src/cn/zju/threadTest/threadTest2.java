package cn.zju.threadTest;

public class threadTest2 implements Runnable{
    private int count = 100;
    public /*synchronized*/ void run(){
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {
        threadTest2 t = new threadTest2();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
