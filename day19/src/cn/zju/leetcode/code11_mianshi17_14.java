package cn.zju.leetcode;

public class code11_mianshi17_14 {
    public static int[] smallestK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        int[] ans = new int[k];
        int pivot = process(arr, 0, arr.length - 1, k - 1);
        int i = 0;
        for (int num : arr) {
            if (num < pivot) {
                ans[i++] = num;
            }
        }
        while (i < k) {
            ans[i++] = pivot;
        }
        return ans;
    }

    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int)(Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
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
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,3,4,5,6,7,8,9};
        int[] ints = smallestK(a, 4);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
