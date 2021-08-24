package cn.zju.leetcode;

import java.util.Arrays;

public class code20_787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int i = 1; i <= k + 1; i++) {
            ans = Math.min(ans, f[i][dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
