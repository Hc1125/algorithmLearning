package cn.zju.leetcode;

import java.util.Arrays;

public class code7_1101 {
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int right = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            right += weights[i];
            left = Math.max(left, weights[i]);
        }
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (process(weights, mid, D)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean process(int[] weights, int s, int limit) {
        int num = 0;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            if (sum > s) {
                sum = weight;
                num++;
                if(num == limit) {
                    return false;
                }
            }
        }
        return true;
    }

    public int shipWithinDays1(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt(), right = Arrays.stream(weights).sum();
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            int need = 1, cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            if (need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
