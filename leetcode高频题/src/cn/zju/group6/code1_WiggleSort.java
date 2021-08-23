package cn.zju.group6;

public class code1_WiggleSort {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                if (i + 1 < n && nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (i + 1 < n && nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
