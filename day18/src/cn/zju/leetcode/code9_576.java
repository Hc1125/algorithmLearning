package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code9_576 {
    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        int base = 1000000007;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startColumn, maxMove});
        int[][] road = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                if (isValid(pos[0], pos[1], m, n)) {
                    ans = (ans + 1) % base;
                } else {
                    if (pos[2] > 0) {
                        if (minPath(pos[0], pos[1], m, n) > pos[2]) {
                            continue;
                        } else {
                            for (int j = 0; j < 4; j++) {
                                int x = pos[0] + road[j][0];
                                int y = pos[1] + road[j][1];
                                queue.add(new int[]{x, y, pos[2] - 1});
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
    public boolean isValid(int i, int j, int m, int n) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }
    public int minPath(int i, int j, int m, int n) {
        return Math.min(Math.min(m - i, n - j), Math.min(i + 1, j + 1));
    }

    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int base = 1000000007;
        int[][] road = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int outCounts = 0;
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];
                    if (count > 0) {
                        for (int[] r : road) {
                            int j1 = j + r[0], k1 = k + r[1];
                            if (isValid(j1, k1, m, n)) {
                                outCounts = (outCounts + count) % base;
                            } else {
                                dpNew[j1][k1] = (dpNew[j1][k1] + count) % base;
                            }
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return outCounts;
    }
}
