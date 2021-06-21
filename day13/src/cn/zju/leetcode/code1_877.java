package cn.zju.leetcode;

public class code1_877 {
    public boolean stoneGame(int[] piles) {
        return first(piles,0, piles.length - 1) > second(piles,0, piles.length - 1);
    }
    public int first(int[] piles, int L, int R) {
        if (L == R) {
            return piles[L];
        }
        return Math.max(piles[L] + second(piles, L + 1, R), piles[R] + second(piles, L , R - 1));
    }

    public int second(int[] piles, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(first(piles, L + 1, R), first(piles, L, R - 1));
    }
    public boolean dp1(int[] piles) {
        int n = piles.length;
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];
        for (int i = 0; i < n; i++) {
            first[i][i] = piles[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                first[i][j] = Math.max(piles[i] + second[i + 1][j], piles[j] + second[i][j - 1]);
                second[i][j] = Math.min(first[i + 1][j], first[i][j - 1]);
            }
        }
        return first[n - 1][n - 1] > second[n - 1][n - 1];
    }
    public boolean dp2(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = piles[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[n - 1] > 0;
    }
}
