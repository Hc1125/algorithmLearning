package cn.zju.zuochengyun.IndexTree;


import java.util.Arrays;

/**
 * 给定一个数组arr，arr[i] = j，表示第i号试题的难度为j。给定一个非负数M
 * 想出一张卷子，对于任何相邻的两道题目，前一题的难度不能超过后一题的难度+M
 * 返回所有可能的卷子种数
 */
public class code3_SequenceM {
    public static int ways1(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, m);
    }
    public static int process(int[] arr, int index, int m) {
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i] + m) {
                    return 0;
                }
            }
            return 1;
        }
        int ans = 0;
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            ans += process(arr, index + 1, m);
            swap(arr, index, i);
        }
        return ans;
    }
    public static void swap(int[] arr, int i, int j) {
        int num = arr[i];
        arr[i] = arr[j];
        arr[j] = num;
    }
    public static int ways2(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        Arrays.sort(arr);
        int all = 1;
        for (int i = 1; i < arr.length; i++) {
            int num = binarySearch(arr, i, arr[i] - m);
            all = all * (num + 1);
        }
        return all;
    }

    public static int binarySearch(int[] arr, int end, int target) {
        int l = 0, r = end;
        int m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return end - l;
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (value + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 20;
        int testTime = 1000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(len, value);
            int m = (int) (Math.random() * (value + 1));
            int ways1 = ways1(arr, m);
            int ways2 = ways2(arr, m);
            if (ways1 != ways2) {
                System.out.println("出错了");
                System.out.println(ways1);
                System.out.println(ways2);
            }
        }
        System.out.println("测试结束");
    }

}
