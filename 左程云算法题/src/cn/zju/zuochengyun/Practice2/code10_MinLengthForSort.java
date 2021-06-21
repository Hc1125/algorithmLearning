package cn.zju.zuochengyun.Practice2;


/**
 * 给定一个无序数组arr，如果只能在一个子数组上排序
 * 返回如果让arr整体有序，需要排序的最短子数组长度
 */
public class code10_MinLengthForSort {
    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = arr[i];
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = arr[i];
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }
}
