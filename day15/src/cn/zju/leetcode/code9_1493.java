package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code9_1493 {
    public static int longestSubarray(int[] nums) {
        int ans = 0;
        int p0 = 0, p1 = 0;
        for (int num : nums) {
            if (num == 0) {
                p1 = p0;
                p0 = 0;
            } else {
                ++p0;
                ++p1;
            }
            ans = Math.max(ans, p1);
        }
        if (ans == nums.length) {
            ans--;
        }
        return ans;
    }

}
