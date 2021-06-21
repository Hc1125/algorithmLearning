package cn.zju.leetcode;


public class code9_154 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int mid = 0;
        while (l < r) {
            mid = ((r - l) >> 1) + l;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return nums[l];
    }


}
