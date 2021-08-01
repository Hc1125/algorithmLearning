package cn.zju;

public class code1_StackOverFlow {
    static class A {
        A a = new A();
//        public void init() {
//            // a = this;
//            a = new A();
//        }
    }

    public static void main(String[] args) {
        // A a = new A();
        // 解决StackOverFlow的问题
        // 1.自己重新写个init方法
        // 2
        // Object o = MyUtils.getUnsafe().allocateInstance(A.class);

    }
}
