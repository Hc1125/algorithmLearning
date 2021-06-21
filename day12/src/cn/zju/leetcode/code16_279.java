package cn.zju.leetcode;

public class code16_279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int base =  (int) Math.sqrt(i);
            if (i == base * base) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= base; j++) {
                    min = Math.min(min, dp[i - j * j]);
                }
                dp[i] = min + 1;
            }

        }
        return dp[n];
    }
}
