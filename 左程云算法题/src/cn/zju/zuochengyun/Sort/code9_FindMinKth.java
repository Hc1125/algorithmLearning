package cn.zju.zuochengyun.Sort;


import java.util.Arrays;

/**
 * 找到无序数组中第k小的数字
 */
public class code9_FindMinKth {

    // k >= 1
    // 迭代版本
    public static int minKth1(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) Math.random() * (R - L + 1)];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    public static int minKth2(int[] array, int k) {
        int[] arr = Arrays.copyOf(array, array.length);
        return process2(arr, 0, arr.length - 1, k - 1);
    }
    /*
        arr 第k小的树
        process2(arr, 0, N-1, k-1)
        arr[L..R] 范围上，如果排序的话（不是真的去排序），找位于index的数
        index [L..R]
     */
    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int)(Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    //利用bfprt算法，时间复杂度O（N）
    public static int minKth3(int[] array, int k) {
        return bfprt(array, 0, array.length - 1, k - 1);
    }
    // arr[L..R] 如果排序的话，位于index位置的数，是什么，返回
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }
    /*
        arr[L..R] 五个数一组
        每个小组内部排序
        每个小组中位数拎出来，组成mArr
        mArr中的中位数，返回
     */
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


}
