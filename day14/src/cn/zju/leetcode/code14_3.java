package cn.zju.leetcode;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class code14_3 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        char[] chars = s.toCharArray();
        int ans = 0;
        int left = 0;
        Set<Character> set = new HashSet<>();
        set.add(chars[0]);
        for (int i = 1; i < n; i++) {
            while (i < n && !set.contains(chars[i])) {
                set.add(chars[i]);
                i++;
            }
            ans = Math.max(ans, i - left);
            while (i < n && chars[left] != chars[i]) {
                set.remove(chars[left++]);
            }
            left++;
        }
        return ans;
    }
}
