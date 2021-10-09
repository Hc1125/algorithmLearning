package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code20_187 {
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() <= 10) {
            return ans;
        }
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.charAt(i));
        }
        map.put(sb.toString(), 1);
        for (int i = 10; i < s.length(); i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                ans.add(str);
            }
        }
        return ans;
    }

    Map<Character, Integer> bin = new HashMap<Character, Integer>() {
        {
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }
    };
    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n <= 10) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < 9; i++) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 9; i < n; i++) {
            x = ((x << 2) | bin.get(s.charAt(i))) & ((1 << 20) - 1);
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) == 2) {
                ans.add(s.substring(i - 9, i + 1));
            }
        }
        return ans;
    }
}
