package cn.zju.leetcode;

public class code17_32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;
        int pre = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                pre = i - 1 - dp[i - 1];
                if (pre >= 0 && s.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}
