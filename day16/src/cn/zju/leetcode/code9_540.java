package cn.zju.leetcode;

public class code9_540 {
    public int singleNonDuplicate1(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        return eor;
    }

    public int singleNonDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1, mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
