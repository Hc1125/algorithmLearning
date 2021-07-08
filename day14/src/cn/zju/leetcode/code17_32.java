package cn.zju.leetcode;

public class code17_32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (i >= 1) {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = 2;
                        dp[i] += i >= 2 ? dp[i - 2] : 0;
                    } else {
                        dp[i] = i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(' ? 2 + dp[i - 1] : 0;
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
