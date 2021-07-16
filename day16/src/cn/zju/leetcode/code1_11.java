package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code1_11 {
    public int maxArea1(int[] height) {
        int high = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < height.length; i++) {
            if (height[i] > high) {
                map.put(height[i], i);
                high = height[i];
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                ans = Math.max(ans, Math.min(entry.getKey(), height[i]) * (i - entry.getValue()));
            }
        }
        return ans;
    }

    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}
