package cn.zju.leetcode;

import java.util.*;

public class code15_502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] info = new int[n][2];
        for (int i = 0; i < n; i++) {
            info[i][0] = profits[i];
            info[i][1] = capital[i];
        }
        Arrays.sort(info, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int curr = 0;
        for (int i = 0; i < k; i++) {
            while (curr < n && info[curr][1] <= w) {
                pq.add(info[curr][0]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }
        return w;
    }
}
