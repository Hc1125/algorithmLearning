package cn.zju.zuochengyun.ArrayAccumulationSumProblem;

/**
 * 数组子数组累加和等于K的最长子数组长度为多少
 * 数组全部为正数情况下
 */
public class code1_LongestSumSubArrayLengthInPositiveArray {
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int L = 0;
        int R = 0;
        int sum = arr[0];
        int len = 0;
        while (R < arr.length) {
            if (sum == K) {
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            } else if (sum < K) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }
        return len;
    }

}
