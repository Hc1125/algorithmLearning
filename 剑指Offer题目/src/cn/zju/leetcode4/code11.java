package cn.zju.leetcode4;

import java.util.HashMap;
import java.util.Map;

public class code11 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
