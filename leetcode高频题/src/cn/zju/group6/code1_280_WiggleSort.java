package cn.zju.group6;
// 给你一个无序的数组 nums, 将该数字 原地 重排后使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。
// 示例:
//        输入: nums = [3,5,2,1,6,4]
//        输出: 一个可能的解答是 [3,5,1,6,2,4]
public class code1_280_WiggleSort {
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
