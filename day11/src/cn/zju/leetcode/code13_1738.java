package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class code13_1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] xor = new int[m + 1][n + 1];
        int[] arr = new int[m * n];
        int index = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                xor[i][j] = xor[i - 1][j] ^ xor[i][j - 1] ^ xor[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                arr[index++] = xor[i][j];
            }
        }
        return bfprt(arr, 0, arr.length - 1, m * n - k);
    }
    public int bfprt(int[] arr, int L, int R, int index) {
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
    public int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    public int[] partition(int[] arr, int L, int R, int pivot) {
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
        return new int[]{less +1, more - 1};
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
