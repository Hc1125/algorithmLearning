package cn.zju.zuochengyun.DynamicProgramming;


import java.util.Arrays;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class code17_SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        if ((n & 1) == 0) {
            return process(arr, 0, n / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, n / 2, sum / 2), process(arr, 0, n / 2 + 1, sum / 2));
        }

    }
    public static int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process(arr ,i + 1, picks, rest);
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = process(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int m = (n + 1) / 2;
        int sum = Arrays.stream(arr).sum();
        sum /= 2;
        int[][][] dp = new int[n + 1][m + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[n][0][rest] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int picks = 0; picks <= m; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[i + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (picks >= 1 && arr[i] <= rest) {
                        next = dp[i + 1][picks - 1][rest - arr[i]];
                    }
                    if (next != -1) {
                        p2 = arr[i] + next;
                    }
                    dp[i][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((n & 1) == 0) {
            return dp[0][n / 2][sum];
        } else {
            return Math.max(dp[0][n / 2][sum], dp[0][m][sum]);
        }
    }
}
