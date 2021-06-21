package cn.zju.InterView3;

import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class code11_TransferQueue {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 26; i++) {
                    queue.transfer((i + 1) + " ");
                    System.out.print(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print(queue.take());
                    queue.transfer((char) ('A' + i) +" ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
