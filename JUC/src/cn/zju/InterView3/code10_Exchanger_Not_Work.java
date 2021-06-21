package cn.zju.InterView3;

import java.util.concurrent.Exchanger;

public class code10_Exchanger_Not_Work {
    private static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((i + 1) + " ");
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print((char) ('A' + i) +" ");
            }
        },"t2").start();
    }
}
