package cn.zju.zuochengyun.Practice1;

/**
 *   括号有效配对：
 *   1）任何一个左括号都能找到和其正确配对的右括号
 *   2）任何一个右括号都能找到和其正确配对的左括号
 *   返回一个括号字符串中，最长的括号有效子串的长度
 */
public class code3_ParenthesesDeep {
    public static int needParenthneses(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n];
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}
