package cn.zju.leetcode1;

public class code2_21 {
    public int[] exchange(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right--;
                left--;
            }
            left++;
        }
        return nums;
    }
}
