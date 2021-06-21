package cn.zju.zuochengyun.Practice2;

/**
 * （1）给定一个字符串，如果可以在字符串任意位置添加字符，最少添加几个字符能让字符串整体都是回文串
 * （2）进阶：返回添加最少字符构成的整体回文串是什么
 */
public class code18_PalindromeMinAdd {
    public static String getPalindrome(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chars = str.toCharArray();
        int[][] dp = getDP(chars);
        char[] res = new char[chars.length + dp[0][chars.length - 1]];
        int i = 0;
        int j = chars.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        while (i <= j) {
            if (chars[i] == chars[j]) {
                res[resl++] = chars[i++];
                res[resr--] = chars[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {
                res[resl++] = chars[j];
                res[resr--] = chars[j--];
            } else {
                res[resl++] = chars[i];
                res[resr--] = chars[i++];
            }
        }
        return String.valueOf(res);
    }
    // dp[i][j] 代表 i到j的子字符串最少加几个字符可以变成回文串
    public static int[][] getDP(char[] str) {
        int[][] dp = new int[str.length][str.length];
        for (int j = 1; j < str.length; j++) {
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }
}
