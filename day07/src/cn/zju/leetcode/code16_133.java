package cn.zju.leetcode;

import java.util.Arrays;

public class code16_133 {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        int[] a = new int[n];
        Arrays.fill(a, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (f[0][i]) {
                a[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (f[j + 1][i]) {
                    a[i] = Math.min(a[i], a[j] + 1);
                }
            }
        }
        return a[n - 1];
    }
}
