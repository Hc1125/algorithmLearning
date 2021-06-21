package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code8_525 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            pre += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(pre)) {
                ans = Math.max(ans, i - map.get(pre));
            } else {
                map.put(pre, i);
            }
        }
        return ans;
    }
}
