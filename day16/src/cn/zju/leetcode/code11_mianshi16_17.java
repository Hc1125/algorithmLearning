package cn.zju.leetcode;

public class code11_mianshi16_17 {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            sum = Math.max(sum, num);
            ans = Math.max(sum, ans);
        }
        return ans;
    }
}
