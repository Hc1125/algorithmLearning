package cn.zju.leetcode5;

import java.util.*;

public class code13_33 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] cnt = new int[26];
            for (int i = 0; i < str.length(); i++) {
                cnt[str.charAt(i) - 'a']++;
            }
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                ans.append(cnt[i]);
                ans.append((char) ('a' + i));
            }
            String key = ans.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
