package cn.zju.zuochengyun.MonotonousStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个数组arr
 * 返回所有子数组最小值的累加和
 */
public class code2_SumOfSubarrayMinimums {

    public static int subArrayMinSum(int[] arr) {
        // left[i] = x : arr[i]左边，离arr[i]最近，<=arr[i]的数，位置在x
        // right[i] = y : arr[i]右边，离arr[i]最近，<arr[i]的数，位置在y
        int[] left = nearLessEqualLeft(arr);
        int[] right = nearLessRight(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long)arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public static int[] nearLessEqualLeft(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peekLast()]) {
                left[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pollLast()] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peekLast()]) {
                right[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pollLast()] = N;
        }
        return right;
    }
}
