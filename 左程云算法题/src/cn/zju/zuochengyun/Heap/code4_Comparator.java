package cn.zju.zuochengyun.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class code4_Comparator {
    public static class Student{
        public String name;
        public int id;
        public int age;
        public Student(String name, int id, int age){
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

    }
}
