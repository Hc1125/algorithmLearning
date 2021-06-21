package cn.zju.leetcode;

public class code11_1594 {
    public int maxProductPath(int[][] grid) {
        final int base = 1000000007;
        int m = grid.length;
        int n = grid[0].length;
        long[] maxArr = new long[n];
        long[] minArr = new long[n];
        maxArr[0] = minArr[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            minArr[i] = maxArr[i] = maxArr[i - 1] * grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            minArr[0] = maxArr[0] = maxArr[0] * grid[i][0];
            long temp = 0;
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    maxArr[j] = Math.max(maxArr[j], maxArr[j - 1]) * grid[i][j];
                    minArr[j] = Math.min(minArr[j], minArr[j - 1]) * grid[i][j];
                } else {
                    temp = maxArr[j];
                    maxArr[j] = Math.min(minArr[j], minArr[j - 1]) * grid[i][j];
                    minArr[j] = Math.max(temp, maxArr[j - 1]) * grid[i][j];
                }
            }
        }
        return maxArr[n - 1] >= 0 ? (int)(maxArr[n - 1] % base) : -1;
    }
}
