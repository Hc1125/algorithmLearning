package cn.zju.leetcode;

import java.util.*;

public class code4_451 {
    public String frequencySort1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list,(a, b) -> map.get(b) - map.get(a));
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            int frequency = map.get(c);
            for (int i = 0; i < frequency; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 桶排序
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(c));
        }
        StringBuilder[] bucket = new StringBuilder[maxFreq + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new StringBuilder();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            bucket[entry.getValue()].append(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq; i > 0; i--) {
            int length = bucket[i].length();
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket[i].charAt(j));
                }
            }
        }
        return sb.toString();
    }

}
