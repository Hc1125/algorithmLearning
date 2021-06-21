package cn.zju.leetcode3;

public class code2_66 {
    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) {
            return new int[]{};
        }
        int[] ans = new int[n];
        int[] pre = new int[n];
        int[] next = new int[n];
        pre[0] = 1;
        next[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * a[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            next[i] = next[i + 1] * a[i + 1];
        }
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i] * next[i];
        }
        return ans;
    }
    public int[] constructArr1(int[] a) {
        if (a.length == 0) return new int[0];
        int[] b = new int[a.length];
        b[0] = 1;
        int tmp = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }
}
