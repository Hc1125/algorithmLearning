package cn.zju.zuochengyun.Practice1;

/**
 * 给定给一个数组arr，返回子数组的最大累加和
 */
public class code17_SubArrayMaxSum {
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}
