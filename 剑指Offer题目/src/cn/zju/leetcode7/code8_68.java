package cn.zju.leetcode7;

public class code8_68 {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int m = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] == target) {
                return m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
