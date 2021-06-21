package cn.zju.threadTest;

public class threadTest10_DCL {
    private static threadTest10_DCL INSTANCE;
    private threadTest10_DCL() {
    }
    public static threadTest10_DCL getInstance(){
        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new threadTest10_DCL();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(threadTest10_DCL.getInstance().hashCode())
            ).start();
        }
    }
}
