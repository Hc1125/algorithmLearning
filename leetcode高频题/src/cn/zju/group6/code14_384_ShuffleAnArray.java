package cn.zju.group6;

public class code14_384_ShuffleAnArray {
    class Solution {
        private int[] origin;
        private int[] shuffle;
        private int N;

        public Solution(int[] nums) {
            origin = nums;
            N = nums.length;
            shuffle = new int[N];
            for (int i = 0; i < N; i++) {
                shuffle[i] = nums[i];
            }
        }

        public int[] reset() {
            return origin;
        }

        public int[] shuffle() {
            for (int i = N - 1; i >= 0; i--) {
                int r = (int) (Math.random() * (i + 1));
                int tmp = shuffle[r];
                shuffle[r] = shuffle[i];
                shuffle[i] = tmp;
            }
            return shuffle;
        }
    }
}
