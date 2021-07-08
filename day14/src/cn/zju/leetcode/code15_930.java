package cn.zju.leetcode;

public class code15_930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int sum1 = 0, sum2 = 0;
        int left1 = 0, left2 = 0, right = 0;
        int ans = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1++];
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2++];
            }
            ans += left2 - left1;
            right++;
        }
        return ans;
    }
}
