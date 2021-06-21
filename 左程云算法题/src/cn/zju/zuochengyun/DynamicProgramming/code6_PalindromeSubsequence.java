package cn.zju.zuochengyun.DynamicProgramming;

/**
 * 一个字符串的最长回文子序列
 */
public class code6_PalindromeSubsequence {
    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    public static int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = process(str, L + 1, R - 1);
        int p2 = process(str, L, R - 1);
        int p3 = process(str, L + 1, R);
        int p4 = str[L] == str[R] ? p1 + 2 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        dp[N - 1][N - 1] = 1;
        for (int index = 2; index < N; index++) {
            int i = 0, j = index;
            while (i < N && j < N) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
                i++;
                j++;
            }
        }
        return dp[0][N - 1];
    }
}
