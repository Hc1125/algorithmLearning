package cn.zju.zuochengyun.Sort;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.DelayQueue;

public class code8_PartitionAndQuickSort {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[] {-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0 , arr.length - 1);
    }
    public static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, l, r);
        process2(arr, l, equalArea[0]- 1);
        process2(arr, equalArea[1] + 1, r);
    }
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0 , arr.length - 1);
    }
    public static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int)(Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        process2(arr, l, equalArea[0]- 1);
        process2(arr, equalArea[1] + 1, r);
    }




    public static class Op {
        public int l;
        public int r;
        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }
    // 非递归版本
    public static void quickSort4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        swap(arr ,(int)(Math.random() * n), n - 1);
        int[] equalArea = netherlandsFlag(arr, 0, n - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Deque<Op> stack = new ArrayDeque<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, n - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int)(Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }
}
