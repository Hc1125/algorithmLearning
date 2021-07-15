package cn.zju.leetcode;

import java.util.Random;

public class code14_384 {
    class Solution {
        int[] origin;
        int[] array;
        Random rand = new Random();
        public Solution(int[] nums) {
            array = nums;
            origin = nums.clone();
        }
        public int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }
        public void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        public int[] reset() {
            array = origin;
            origin = origin.clone();
            return origin;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }
}
