package cn.zju.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code8_509 {
    public static void main(String[] args) {
        System.out.println(8>>1);
    }
    private static int fib(int n){
        if(n<2){
            return n;
        }
        Deque<Integer> deque = new LinkedList<Integer>();
        deque.add(0);
        deque.add(1);
        int i = 2;
        int num = 0;
        while(i<=n){
            num = deque.pollFirst()+deque.peekFirst();
            deque.addLast(num);
            i++;
        }
        return deque.peekLast();
    }
    private  static int fib1(int n){
        if(n<2){
            return n;
        }
        return fib1(n-2)+fib1(n-1);
    }
    private static int fib2(int n){
        if(n<2){
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    private static int fib3(int n){
        if(n<2){
            return n;
        }
        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q,n-1);
        return res[0][0];
    }
    private static int[][] pow(int[][] a, int n){
        int[][] ret = {{1,0},{0,1}};
        while(n>0){
            if((n & 1) == 1){
                ret = multiply(ret,a);
            }
            n >>= 1;
            a = multiply(a,a);
        }
        return ret;
    }
    private static int[][] multiply(int[][] a, int[][] b){
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
