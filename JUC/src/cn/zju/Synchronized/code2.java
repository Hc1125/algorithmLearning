package cn.zju.Synchronized;

public class code2 {
    public static void main(String[] args) {
        long a = 1;
        int b = 1;
        Long c = new Long(1);
        Integer d = new Integer(1);
        System.out.println(a == b);
        System.out.println(c.equals(d));
//        System.out.println(c == d);

    }
}
