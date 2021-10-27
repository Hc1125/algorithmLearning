package cn.zju.group6;

/**
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 */
public class code9_340_LongestSubstringWithAtMostKDistinctCharacters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] count = new int[256];
        int diff = 0;
        int R = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            while (R < N && (diff < k || (diff == k && count[str[R]] > 0))) {
                diff += count[str[R]] == 0 ? 1 : 0;
                count[str[R++]]++;
            }
            ans = Math.max(ans, R - i);
            diff -= count[str[i]] == 1 ? 1 : 0;
            count[str[i]]--;
        }
        return ans;
    }
}
