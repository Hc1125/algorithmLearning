package cn.zju.leetcode;

public class code16_jianzhi53_I {
    public int search(int[] nums, int target) {
        int begin = binarySearch(nums, target);
        int ans = 0;
        for (int i = begin; i < nums.length; i++) {
            if (target == nums[i]) {
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
