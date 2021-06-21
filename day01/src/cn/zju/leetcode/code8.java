package cn.zju.leetcode;

import java.lang.reflect.Field;

public class code8 {
    public static void main(String[] args) throws Exception {
        Integer a = 1,b = 2;
        System.out.println("before:a="+a+",b="+b);
        swap(a,b);
        System.out.println("after:a="+a+",b="+b);
    }
    public static void swap(Integer i1,Integer i2) throws Exception {
//        Integer temp = i1;
//        i1 = i2;
//        i2 = temp;
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        Integer tmp = new Integer(i1.intValue());
        field.set(i1,i2.intValue());
        field.set(i2,tmp);
    }
}
