package cn.zju.leetcode;

import java.util.Arrays;

public class code9_453 {
    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int ans = 0;
        for (int num : nums) {
            ans += num - min;
        }
        return ans;
    }
}
