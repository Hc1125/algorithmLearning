package cn.zju.leetcode;

public class code1_1833 {
    public static int maxIceCream(int[] costs, int coins) {
        int[] dp = new int[coins + 1];
        for (int i = costs[0]; i <= coins; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < costs.length; i++) {
            int[] next = new int[coins + 1];
            for (int j = 0; j <= coins; j++) {
                next[j] = dp[j];
                if (j >= coins) {
                    next[j] = Math.max(next[j], dp[j - costs[i]] + 1);
                }
                dp = next;
            }

        }
        return dp[coins];
    }

}
