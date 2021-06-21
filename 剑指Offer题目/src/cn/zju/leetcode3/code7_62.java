package cn.zju.leetcode3;

import java.util.LinkedList;

public class code7_62 {
    public int lastRemaining(int n, int m) {
        return f(n, m);
    }
    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }
    public int lastRemaining1(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; i++) {
            f = (m + f) % i;
        }
        return f;
    }
}
