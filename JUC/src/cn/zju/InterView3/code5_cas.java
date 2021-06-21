package cn.zju.InterView3;


public class code5_cas {
    enum ReadyToRun {T1, T2}
    static volatile ReadyToRun r = ReadyToRun.T1;
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (r != ReadyToRun.T1) {}
                System.out.print((i + 1) + " ");
                r = ReadyToRun.T2;
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (r != ReadyToRun.T2) {}
                System.out.print((char) ('A' + i) +" ");
                r = ReadyToRun.T1;
            }
        },"t2").start();
    }
}
