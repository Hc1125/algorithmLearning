package cn.zju.leetcode;

public class code8_485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int ans = 0;
        while(right < n && left < n) {
            if(nums[left] == 1) {
                right = left;
                while(right < n && nums[right] == 1) {
                    right++;
                }
                ans = Math.max(ans, right - left);
                left = right;
            }
            left++;
        }
        return ans;
    }
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
