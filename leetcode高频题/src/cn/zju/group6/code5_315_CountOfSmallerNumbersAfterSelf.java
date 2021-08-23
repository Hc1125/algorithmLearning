package cn.zju.group6;

import java.util.ArrayList;
import java.util.List;

public class code5_315_CountOfSmallerNumbersAfterSelf {
    public class Node {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
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

    public void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m, ans);
        process(arr, m + 1, r, ans);
        merge(arr, l, m, r, ans);
    }

    public void merge(Node[] arr, int l, int m, int r, List<Integer> ans) {
        Node[] help = new Node[r - l + 1];
        int i = help.length - 1;
        int p1 = m, p2 = r;
        while (p1 >= l && p2 > m) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - m);
            }
            help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }
}
