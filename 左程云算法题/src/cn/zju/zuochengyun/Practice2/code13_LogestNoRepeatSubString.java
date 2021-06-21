package cn.zju.zuochengyun.Practice2;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个字符串中找到没有重复字符子串中最长的长度
 * 例如：
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 */
public class code13_LogestNoRepeatSubString {
    public static int longestNoRepeatSubString(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int[] dp = new int[s.length()];
        // dp[i]含义：必须以i位置字符为结尾的最长无重复子串长度
        Map<Character, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(s.charAt(0), 0);
        int ans = 1;
        for (int i = 1; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int begin = Math.max(map.get(s.charAt(i)), i - 1 - dp[i - 1]);
                dp[i] = i - begin;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            map.put(s.charAt(i), i);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String s1 = "xxxxxxsxxxxxx";
        System.out.println(longestNoRepeatSubString(s1));
    }
}
