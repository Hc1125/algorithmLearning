package cn.zju.leetcode;

public class code7_81 {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int[] check = new int[2 * n];
        for (int i = n; i < 2 * n; i++) {
            check[i] = nums[i - n];
            check[i - n] = nums[i - n];
        }
        int i = 0;
        while (i < n && check[i] <= check[i + 1]) {
            i++;
        }

        int begin = i == n ? n : i + 1, end = begin + n - 1;
        return binarySearch(check, begin, end, target);
    }
    public boolean binarySearch(int[] nums, int l, int r, int target) {
        int m = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return true;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }
    public boolean search1(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (nums[m] == target) {
                return true;
            }
            if (nums[l] == nums[m] && nums[m] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m] < target && target <= nums[n - 1]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return false;
    }
}
