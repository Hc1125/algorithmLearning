package cn.zju.leetcode8;

public class code10_90 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        int ans = b;
        a = nums[1];
        b = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return Math.max(ans, b);
    }
}
