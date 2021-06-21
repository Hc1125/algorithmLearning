package cn.zju.InterView3;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class code8_PipedStream {
    public static void main(String[] args) throws Exception {
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();
        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (int i = 0; i < 26; i++) {
                    System.out.print((i + 1) + " ");
                    output1.write(msg.getBytes());
                    input1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (int i = 0; i < 26; i++) {
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print((char) ('A' + i) +" ");
                    }
                    output2.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
