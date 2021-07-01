package cn.zju.leetcode;

import java.util.*;

public class code16_LCP07 {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : relation) {
            List<Integer> list = map.getOrDefault(r[0], new ArrayList<>());
            list.add(r[1]);
            map.put(r[0], list);
        }
        return process(0, 0, map, n, k);
    }

    public int process(int step, int i, Map<Integer, List<Integer>> map, int n, int k) {
        if(step == k) {
            return i == n - 1 ? 1 : 0;
        }
        List<Integer> list = map.getOrDefault(i, new ArrayList<>());
        int ans = 0;
        for (int next : list) {
            ans += process(step + 1, next, map, n, k);
        }
        return ans;
    }

    public int dp(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : relation) {
            List<Integer> list = map.getOrDefault(r[0], new ArrayList<>());
            list.add(r[1]);
            map.put(r[0], list);
        }
        int[][] dp = new int[n][k + 1];
        dp[n - 1][k] = 1;
        for (int j = k - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                List<Integer> list = map.getOrDefault(i, new ArrayList<>());
                for (int next : list) {
                    dp[i][j] += dp[next][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public int dp2(int n, int[][] relation, int k) {
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int i = k - 1; i >= 0; i--) {
            int[] next = new int[n];
            for (int[] r : relation) {
                next[r[0]] += dp[r[1]];
            }
            dp = next;
        }
        return dp[0];
    }


}
