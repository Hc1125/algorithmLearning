package cn.zju.leetcode2;

public class code17_53_II {
    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == mid) i = mid + 1;
            else j = mid - 1;
        }
        return i;
    }
}
