package cn.zju.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class code3_313 {
    public int nthSuperUglyNumber1(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.offer(1L);
        set.add(1L);
        int i = 0;
        int ans = 1;
        while (i < n) {
            long temp = pq.poll();
            ans = (int) temp;
            for (int prime : primes) {
                if (set.add(temp * prime)) {
                    pq.offer(temp * prime);
                }
            }
            i++;
        }
        return ans;
    }
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(nums[j], minNum);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }
}
