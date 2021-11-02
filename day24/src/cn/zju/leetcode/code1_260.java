package cn.zju.leetcode;

public class code1_260 {
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        int t = (eor == Integer.MIN_VALUE ? eor : eor & (-eor));
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & t) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[]{num1, num2};
    }
}
