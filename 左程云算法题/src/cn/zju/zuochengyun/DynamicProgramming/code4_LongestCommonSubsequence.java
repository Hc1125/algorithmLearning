package cn.zju.zuochengyun.DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

/**
 * 两个字符串的最长公共子序列
 */
public class code4_LongestCommonSubsequence {
    public static int lcs(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        return process(str1, str2, N - 1, M - 1);
    }

    public static int process(char[] str1, char[] str2, int i1, int i2) {
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : 0;
        }
        if (i1 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2 - 1) == 1) ? 1 : 0;
        }
        if (i2 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1 - 1, i2) == 1) ? 1 : 0;
        }
        // i1 和 i2都不是0
        // 最长公共子序列结尾，不是以str1[i1]与str[i2]结尾的
        int p1 = process(str1, str2, i1, i2 - 1);
        int p2 = process(str1, str2, i1 - 1, i2);
        int p3 = str1[i1] == str2[i2] ? (1 + process(str1, str2, i1 - 1, i2 - 1)) : 0;
        return Math.max(p1,Math.max(p2, p3));
    }

    public static int dp(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {

    }
}
