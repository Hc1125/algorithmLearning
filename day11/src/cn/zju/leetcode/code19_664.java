package cn.zju.leetcode;

public class code19_664 {
    public int strangePrinter(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 1) {
            return 1;
        }
        return process(chars, 0,n - 1);
    }
    public int process(char[] chars, int begin, int end) {
        if (begin == end) {
            return 1;
        }
        if (chars[begin] == chars[end]) {
            return process(chars, begin, end - 1);
        } else {
            int min = Integer.MAX_VALUE;
            for (int k = begin; k < end; k++) {
                min = Math.min(process(chars, begin, k) + process(chars, k + 1, end), min);
            }
            return min;
        }
    }

    public int dp(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(dp[i][k] + dp[k + 1][j], min);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n - 1];
    }

}
