package cn.zju.leetcode;

public class code2_162 {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        int l = 1, r = n - 2;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] <= nums[mid - 1]) {
                r = mid - 1;
            } else if (nums[mid] <= nums[mid + 1]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
