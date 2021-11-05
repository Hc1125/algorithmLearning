package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code7_1218 {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num - difference, 0) + 1);
            ans = Math.max(ans, map.get(num));
        }
        return ans;
    }
}
