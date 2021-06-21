package cn.zju.leetcode1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class code10_40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || arr.length == k) {
            return arr;
        }
        if (k == 0) {
            return new int[]{};
        }
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (arr.length == 0 || arr.length == k) {
            return arr;
        }
        if (k == 0) {
            return new int[]{};
        }
        int[] ans = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (pq.peek() > arr[i]) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }
        return ans;
    }
}
