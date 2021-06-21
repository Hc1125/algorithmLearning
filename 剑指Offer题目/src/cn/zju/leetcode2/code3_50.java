package cn.zju.leetcode2;

import java.util.*;

public class code3_50 {
    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(chars[i] ,map.getOrDefault(chars[i],0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(chars[i]) == 1) {
                return chars[i];
            }
        }
        return ' ';
    }
    public char firstUniqChar1(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : chars) {
            map.put(c, !map.containsKey(c));
        }
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) return entry.getKey();
        }
        return ' ';
    }
}
