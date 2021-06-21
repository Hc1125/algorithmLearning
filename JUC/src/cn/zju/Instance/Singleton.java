package cn.zju.Instance;

public class Singleton {
    private Singleton() {

    }
    public static Singleton getInstance() {
        return InnerClass.singleton;
    }
    public static class InnerClass {
        private static final Singleton singleton = new Singleton();
    }


}
