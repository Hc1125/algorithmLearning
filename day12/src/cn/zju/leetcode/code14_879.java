package cn.zju.leetcode;

import java.util.Arrays;

public class code14_879 {
    public int base = 1000000007;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        return process(n, minProfit, group, profit, 0, 0, n);
    }
    public int process(int n, int minProfit, int[] group, int[] profit, int i, int sum, int num) {
        if (num == 0) {
            return sum >= minProfit ? 1 : 0;
        }
        if (i == group.length) {
            return sum >= minProfit ? 1 : 0;
        }
        if (group[i] <= num) {
            return (process(n, minProfit, group, profit, i + 1, sum + profit[i], num - group[i]) % base) +
                    (process(n, minProfit, group, profit, i + 1, sum, num) % base) ;
        } else {
            return process(n, minProfit, group, profit, i + 1, sum, num) % base;
        }
    }

    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        int sum = Arrays.stream(profit).sum() % base;
        int[][][] dp = new int[group.length + 1][sum + 1][n + 1];
        for (int i = minProfit; i <= sum; i++) {
            for (int j = 0; j <= n; j++) {
                dp[group.length][i][j] = 1;
            }
        }
        for (int i = group.length - 1; i >= 0; i--) {
            for (int j = sum; j >= 0; j--) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i + 1][j][k];
                    if (k >= group[i] && (j + profit[i]) <= sum) {
                        dp[i][j][k] = (dp[i + 1][j + profit[i]][k - group[i]] + dp[i][j][k]) % base;
                    }
                }
            }
        }
        return dp[0][0][n];
    }
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            int member = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < member) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - member][Math.max(0, k - earn)]) % base;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[group.length][i][minProfit]) % base;
        }
        return sum;
    }

    public int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            int member = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= member; j--) {
                for (int k = minProfit; k >= 0; k--) {
                        dp[j][k] = (dp[j][k] + dp[j - member][Math.max(0, k - earn)]) % base;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][minProfit]) % base;
        }
        return sum;
    }

}
