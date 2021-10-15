package cn.zju.leetcode4;

import java.util.HashMap;
import java.util.Map;

public class code10 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 1);
        int ans = 0;
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
