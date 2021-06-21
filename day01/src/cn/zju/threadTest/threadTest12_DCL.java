package cn.zju.threadTest;

public class threadTest12_DCL {
    private static volatile threadTest12_DCL INSTANCE;
    private threadTest12_DCL() {
    }
    public static threadTest12_DCL getInstance(){
        if(INSTANCE == null){
            synchronized (threadTest10_DCL.class){
                if(INSTANCE == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new threadTest12_DCL();
                }
            }
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(threadTest12_DCL.getInstance().hashCode())
            ).start();
        }
    }
}
