package cn.zju.zuochengyun.Practice2;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

/**
 * 将字符串切成每部分都是回文串
 * 能切出来的部分的最少数量
 */
public class code6_PMinParts {
    public static int minParts(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] isP = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            isP[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            isP[i][i + 1] = str[i] == str[i + 1];
        }
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N ; col++) {
                isP[row][col] = str[row] == str[col] && isP[row + 1][col - 1];
            }
        }
        int[] dp = new int[N + 1];
        // dp[i]代表从i开始到结尾的部分能截出全是回文串的最少部分数量
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if (isP[i][end]) {
                    dp[i] = Math.min(dp[i], dp[end + 1] + 1);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFK";
        System.out.println(minParts(test));
    }
}
