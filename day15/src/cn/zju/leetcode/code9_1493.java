package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code9_1493 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;
        List<Integer> beginList = new ArrayList<>();
        Map<Integer, Integer> beginMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int begin = i;
                while (i < n && nums[i] == 1) {
                    i++;
                }
                beginMap.put(begin, i - begin);
                endMap.put(i, i - begin);
                if (i - begin > max) {
                    max = i - begin;
                    beginList.clear();
                    beginList.add(begin);
                } else if (i - begin == max) {
                    beginList.add(begin);
                }
            }
        }
        if (max == n) {
            return n - 1;
        }
        int ans = 0;
        for (int i = 0; i < beginList.size(); i++) {
            int begin = beginList.get(i);
            int end = begin + max;
            if (begin - 2 >= 0 && endMap.containsKey(begin - 2)) {
                ans = Math.max(ans, endMap.get(begin - 2) + max);
            }
            if (end + 1 < n && beginMap.containsKey(end + 1)) {
                ans = Math.max(ans, beginMap.get(end + 1) + max);
            }
        }
        return ans;
    }
}
