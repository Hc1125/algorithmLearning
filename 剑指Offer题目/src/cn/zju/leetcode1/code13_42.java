package cn.zju.leetcode1;

public class code13_42 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int ret = nums[0];
        int ans = ret;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret + nums[i], nums[i]);
            ans = Math.max(ret, ans);
        }
        return ans;
    }
}
