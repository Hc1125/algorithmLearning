package cn.zju.leetcode;

public class code11_440 {
    public int findKthNumber(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return process(arr, 0, arr.length - 1, k - 1);
    }
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        String pivot = String.valueOf(arr[L + (int)((R - L + 1) * Math.random())]);
        int[] range = partition(arr, L, R, pivot);
        if (range[0] > index) {
            return process(arr, L, range[0] - 1, index);
        } else if (range[1] < index) {
            return process(arr, range[1] + 1, R, index);
        } else {
            return arr[index];
        }
    }

    public int[] partition(int[] arr, int L, int R, String pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (String.valueOf(arr[cur]).compareTo(pivot) < 0) {
                swap(arr, ++less, cur++);
            } else if (String.valueOf(arr[cur]).compareTo(pivot) > 0) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{++less, --more};
    }
}
