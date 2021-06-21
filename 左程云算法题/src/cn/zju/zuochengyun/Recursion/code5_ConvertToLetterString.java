package cn.zju.zuochengyun.Recursion;

import org.w3c.dom.ls.LSOutput;

public class code5_ConvertToLetterString {
    /**
     *  i之前的位置，如何转换已经做过决定了，不用再关心
     *  i... 有多少种转化的结果
     */
    public static int number(String s) {
        char[] str = s.toCharArray();
        return process(str,0);
    }
    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }
    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 < N && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(dpWays("1411411423423453424124123"));
        System.out.println(number("1411411423423453424124123"));
    }
}
