package cn.zju.zuochengyun.Practice3;

import java.util.Arrays;

/**
 * 给定一个无序数组arr，返回如果排序之后，相邻数之间的最大差值
 * {3,1,7,9},如果排序后{1,3,7,9},相邻数之间的最大差值来自3和7，返回4
 * 要求：不能真的进行排序，并且要求在时间复杂度O(N)内解决
 */
public class code12_MaxGap {
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        if (max == min) {
            return 0;
        }
        // 不止一种数，min~max一定有range,  len个数据，准备len+1个桶
        boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
        int[] maxs = new int[len + 1];  // maxs[i] i号桶收集的所有数字的最大值
        int[] mins = new int[len + 1];  // mins[i] i号桶收集的所有数字的最小值
        int bid = 0; // 桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0]; // 上一个非空桶的最大值
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}
