package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code9_13 {
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int[][] direction = {{1, 0}, {0, 1}};
        boolean[] isUsed = new boolean[m * n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        isUsed[0] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int num = queue.poll();
            ans++;
            int i = num / n;
            int j = num - i * n;
            for (int l = 0; l < 2; l++) {
                int row = i + direction[l][0];
                int col = j + direction[l][1];
                if (row >= 0 && row < m && col >= 0 && col < n && !isUsed[row * n + col] && isReached(row, col, k)) {
                    queue.add(row * n + col);
                    isUsed[row * n + col] = true;
                }
            }
        }
        return ans;
    }
    public boolean isReached (int i, int j, int k) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j > 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }
    public int movingCount1(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 && j == 0) || !isReached(i, j, k)) {
                    continue;
                }
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }
}
