package cn.zju.leetcode7;

public class code10_70_SingleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        int m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if ((m & 1) == 1) m--;
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
