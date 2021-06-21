package cn.zju.zuochengyun.Practice2;

/**
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，而且在aim中属于str1的字符之间保持原来str1中的顺序，
 * 属于str2的字符之间保持原来str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是否是str1和str2的交错组成
 * 举例：str1 = "AB"  str2 = "12" 那么"AB12","A1B2","A12B","1A2B","1AB2"等都是str1和str2的交错组成
 */
public class code9_StringCross {
    public static boolean isCross(String s1, String s2, String ai) {
        if (s1 == null || s2 == null || ai == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] aim = ai.toCharArray();
        if (aim.length != str1.length + str2.length) {
            return false;
        }
        /**
         * dp[i][j]代表
         * str1拿前缀i长度，str2拿前缀j长度
         * 能否交错组成str3前缀i+j长度
         */
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i < str1.length; i++) {
            if (str1[i - 1] != aim[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j < str2.length; j++) {
            if (str2[j - 1] != aim[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if ((str1[i - 1] == aim[i + j - 1] && dp[i - 1][j]) || (str2[j - 1] == aim[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

}
