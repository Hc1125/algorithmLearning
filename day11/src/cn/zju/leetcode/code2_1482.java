package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class code2_1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < m * k) {
            return -1;
        }
        int low = 1;
        int high = Arrays.stream(bloomDay).max().getAsInt();
        while (low < high) {
            int days = low + ((high - low) >> 1);
            if (canMake(bloomDay, days, m , k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }
        return low;
    }
    public boolean canMake(int[] bloomDay, int days, int m, int k) {
        int flower = 0;
        int bloom = 0;
        for (int i = 0; i < bloomDay.length && bloom < m; i++) {
            if (bloomDay[i] <= days) {
                flower++;
                if (flower == k) {
                    bloom++;
                    flower = 0;
                }
            } else {
                flower = 0;
            }
        }
        return bloom == m;
    }
}
