package cn.zju.zuochengyun.Practice3;

/**
 * 给定两个字符串S和T，返回S子序列等于T的不同子序列个数有多少个？如果得到子序列A删除的位置与得到子序列B删除的位置不同
 * 那么认为A和B就是不同的
 * 例子：
 * S = "rabbbit", T = "rabbit"
 * 返回：3
 * 是以下三个S的不同自学列，没有的位置表示删除的位置，因为删除的位置不同，所以这三个子序列是不同的
 *
 */
public class code9_DistinctSubsequences {
    public static int numDistinct1(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return process(s, t, s.length, t.length);
    }

    public static int process(char[] s, char[] t, int i, int j) {
        if (j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        int res = process(s, t, i - 1, j);
        if (s[i - 1] == t[j - 1]) {
            res += process(s, t,i - 1,j - 1);
        }
        return res;
    }
    /**
     * dp[i][j]记为s以i结尾的子序列中能构成t以j结尾的子字符串的数量
     * 可能性1：S[..i]的所以子序列中，都不以s[i]结尾，则以dp[i][j]肯定包含dp[i-1][j]
     * 可能性2：S[..i]的所以子序列中，都必须以s[i]结尾
     * 这要求S[i] == T[j] 则以dp[i][j]肯定包含dp[i-1][j-1]
     */
    public static int numDistinct2(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[][] dp = new int[s.length + 1][t.length + 1];
        for (int j = 0; j <= t.length; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= s.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length; i++) {
            for (int j = 1; j <= t.length; j++) {
                dp[i][j] = dp[i - 1][j] + (s[i - 1] == t[j - 1] ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[s.length][t.length];
    }

    public static int numDistinct3(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] dp = new int[t.length + 1];
        dp[0] = 1;
        for (int i = 1; i <= t.length; i++) {
            dp[i] = 0;
        }
        for (int i = 1; i <= s.length; i++) {
            for (int j = t.length; j >= 1; j--) {
                dp[j] += s[i - 1] == t[j - 1] ? dp[j - 1] : 0;
            }
        }
        return dp[t.length];
    }

}
