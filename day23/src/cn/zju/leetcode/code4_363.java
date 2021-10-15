package cn.zju.leetcode;

import java.util.TreeSet;

public class code4_363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                int cur = 0;
                set.add(0);
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c];
                    cur += sum[c];
                    Integer d = set.ceiling(cur - k);
                    if (d != null) {
                        ans = Math.max(ans, cur - d);
                        if (ans == k) {
                            return k;
                        }
                    }
                    set.add(cur);
                }
            }
        }
        return ans;
    }
}
