package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code7_523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 1; i < n; i++) {
            if (sum[i] % k == 0) {
                return true;
            }
            for (int j = 0; j < i - 1; j++) {
                if ((sum[i] - sum[j]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum1(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int mod = 0;
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            mod = (mod + nums[i]) % k;
            if (map.containsKey(mod)) {
                int pre = map.get(mod);
                if (i - pre >= 2) {
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }
        return false;
    }

}
