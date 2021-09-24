package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算左侧大于当前元素的个数
 */
public class code13_315_II {
    public static class Node {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public static List<Integer> countBigger(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        if (nums.length < 2) {
            return ans;
        }
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Node(i, nums[i]);
        }
        process(arr, 0, arr.length - 1, ans);
        return ans;
    }

    public static void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid, ans);
        process(arr, mid + 1, r, ans);
        merge(arr, l, mid, r, ans);
    }

    public static void merge(Node[] arr, int l, int m, int r, List<Integer> ans) {
        Node[] help = new Node[r -l + 1];
        int i = 0;
        int p1 = l, p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p2].index, ans.get(arr[p2].index) + p1 - l + 1);
            }
            help[i++] = arr[p1].value > arr[p2].value ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 4, 3, 1, 5, 2};
        List<Integer> list = countBigger(nums);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
