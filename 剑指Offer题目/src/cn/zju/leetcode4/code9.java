package cn.zju.leetcode4;

public class code9 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int ans = 0;
        int m = 1;
        int l = 0, r = 0;
        int n = nums.length;
        while (r < n) {
            m *= nums[r++];
            while (l < r && m >= k) {
                m /= nums[l++];
            }
            ans += r - l;
        }
        return ans;
    }

}
