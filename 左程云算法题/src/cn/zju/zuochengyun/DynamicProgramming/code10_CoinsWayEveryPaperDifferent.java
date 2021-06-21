package cn.zju.zuochengyun.DynamicProgramming;

/**
 * arr是货币数组，其中的值都是正数，给定一个正数aim
 * 每个值都认为是一张货币
 * 即时是值相同的货币也认为每一张都是不同的
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1} aim = 2
 * 第0个和第1个，第1个和第2个，第0个和第2个都能组成2
 * 一共就三种方法，返回3
 */
public class code10_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
        }
    }

    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][aim];
    }
}
