package cn.zju.JUC;

public class code1_VolatileReality {
    static /*volatile*/ boolean flag;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("start");
            while (!flag) {

            }
            System.out.println("end");
        }).start();
        Thread.sleep(2000);
        /**
         * 这里不涉及任何可见性问题和指令重排序问题
         * 只是JIT即时编译器优化了，过度优化
         * volatile与_asm_volatile都会给一个编译器屏障
         * 防止编译器过度优化
         */
        flag = true;
    }
}
