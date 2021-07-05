package cn.zju.leetcode;

import java.util.Arrays;

public class code5_645 {
    // 利用stream求和
    public int[] findErrorNums1(int[] nums) {
        int setSum = Arrays.stream(nums).distinct().sum();
        return new int[] {
                Arrays.stream(nums).sum() - setSum,
                (1 + nums.length) * nums.length / 2 - setSum
        };
    }

    // 位运算找出出现两次和没出现过的数字
    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
        }
        int lowBit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int num = 1; num <= n; num++) {
            if ((num & lowBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[] {num1, num2};
            }
        }
        return new int[] {num2, num1};
    }

    // 原地标记，把出现过的数字对应位置数字标记成负数
    public int[] findErrorNums3(int[] nums) {
        int dup = -1, miss = -1;
        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            if (nums[idx] < 0) {
                dup = idx + 1;
            }
            nums[idx] = -nums[idx];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && i + 1 != dup) {
                miss = i + 1;
                break;
            }
        }
        return new int[] {dup, miss};
    }
}
