package cn.zju.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class code10_48 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        int ans = 0;
        while (right < n) {
            if (!set.add(chars[right])) {
                ans = Math.max(ans, right - left);
                while (chars[left] != chars[right]) {
                    set.remove(chars[left]);
                    left++;
                }
                left++;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }
    public int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (dic.containsKey(s.charAt(j))) {
                i = Math.max(i, dic.get(s.charAt(j)));
            }
            dic.put(s.charAt(j), j);
            res = Math.max(res, j - i);
        }
        return res;
    }
}
