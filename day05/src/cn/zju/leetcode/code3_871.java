package cn.zju.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class code3_871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations){
        int N = stations.length;
        long[] dp = new long[N + 1];
        dp[0] = startFuel;
        for (int i = 0; i < N; i++) {
            for(int t = i; t >= 0; t--){
                if(dp[t] >= stations[i][0]){
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long)stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= N; i++) {
            if(dp[i] >= target) return i;
        }
        return -1;
    }
    public int minRefuelStops1(int target, int startFuel, int[][] stations){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0, prev = 0;
        for (int[] station : stations) {
            int location = station[0];
            int capacity = station[1];
            startFuel -= location - prev;
            while (!pq.isEmpty() && startFuel < 0){
                startFuel += pq.poll();
                ans++;
            }
            if(startFuel < 0) return -1;
            pq.offer(capacity);
            prev = location;
        }
        startFuel -= target - prev;
        while(!pq.isEmpty() && startFuel < 0){
            startFuel += pq.poll();
            ans++;
        }
        if(startFuel < 0) return -1;
        return ans;
    }
}
