package cn.zju.zuochengyun.DynamicProgramming;

/**
 * 给定一个正数数组arr
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近情况下，较小集合的累加和
 */

public class code16_SplitSumClosed {
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, 0, sum >> 1);
    }
    // arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else {
            int p1 = process(arr , i + 1, rest);
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }

    public static int dp (int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= sum / 2; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (arr[i] <= j) {
                    p2 = arr[i] + dp[i + 1][j - arr[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

}
