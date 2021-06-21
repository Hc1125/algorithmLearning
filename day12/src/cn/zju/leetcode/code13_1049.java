package cn.zju.leetcode;


import java.util.Arrays;

public class code13_1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int m = sum / 2;
        int n = stones.length;
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return Math.abs(sum - 2 * dp[m]);
    }

    public int lastStoneWeightII2(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int m = sum / 2;
        int n = stones.length;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= stones[i]; j--) {
                dp[j] |= dp[j - stones[i]];
            }
        }
        for (int j = m;; j--) {
            if (dp[j]) {
                return sum - 2 * j;
            }
        }
    }
}
