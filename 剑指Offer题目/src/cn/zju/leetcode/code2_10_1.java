package cn.zju.leetcode;

import org.w3c.dom.ls.LSOutput;

public class code2_10_1 {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % (int)(1e9 + 7);
        }
        return arr[n];
    }
    public int fib2(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % (int)(1e9 + 7);
            a = b;
            b = sum;
        }
        return a;
    }
}
