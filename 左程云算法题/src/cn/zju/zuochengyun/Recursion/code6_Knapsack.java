package cn.zju.zuochengyun.Recursion;

import java.net.Inet4Address;

/**
 * 经典背包问题
 * 所有的货，重量和价值，都在w和v数组里
 * bag背包容量不能超过这个载重
 * 返回：在不超重的情况下，能够得到的最大价值
 */
public class code6_Knapsack {
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }
    // 不变： w[] v[] bag
    // index...最大价值
    // 0..index-1上做了货物的选择，使得你已经达到的重量是多少alreadyW
    // 如果返回-1，认为没有方案
    // 如果不返回-1，认为返回的值是真实价值
    public static int process(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, bag);
        int p2 = 0;
        int next = process(w, v, index + 1, bag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }
    /*
        只剩下rest的空间了
        index...货物自由选择，但是剩余空间不要小于0
        返回index...货物能够获得的最大价值
     */
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...]都是0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

}
