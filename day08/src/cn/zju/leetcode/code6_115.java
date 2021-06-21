package cn.zju.leetcode;

public class code6_115 {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        return process(s, t, 0, 0);
    }
    public int process (String s, String t, int i, int j) {
        if (j == t.length()) {
            return 1;
        }
        if (i == s.length()) {
            return 0;
        }
        if (s.charAt(i) == t.charAt(j)) {
            return process(s, t, i + 1, j) + process(s, t, i + 1, j + 1);
        } else {
            return process(s, t, i + 1, j);
        }
    }
    public int numDistinct1(String s, String t) {
       int m = s.length();
       int n = t.length();
       if (m < n) {
           return 0;
       }
       int[][] dp = new int[m + 1][n + 1];
       for (int i = 0; i <= m; i++) {
           dp[i][n] = 1;
       }
       for (int i = m - 1; i >= 0; i--) {
           char sChar = s.charAt(i);
           for (int j = n - 1; j >=0; j--) {
               char tChar = t.charAt(j);
               if (sChar == tChar) {
                   dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
               } else {
                   dp[i][j] = dp[i + 1][j];
               }
           }
       }
       return dp[0][0];
    }
}
