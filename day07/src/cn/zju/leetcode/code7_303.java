package cn.zju.leetcode;

public class code7_303 {
    class NumArray1{
        public int[] nums;
        public NumArray1(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
            return sum;
        }
    }

    class NumArray {
        int[] sums;
        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
        }
        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }
    }
}
