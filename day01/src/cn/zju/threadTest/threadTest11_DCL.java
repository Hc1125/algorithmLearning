package cn.zju.threadTest;

public class threadTest11_DCL {
    private static threadTest11_DCL INSTANCE;
    private threadTest11_DCL() {
    }
    public static synchronized threadTest11_DCL getInstance(){
        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new threadTest11_DCL();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(threadTest11_DCL.getInstance().hashCode())
            ).start();
        }
    }
}
