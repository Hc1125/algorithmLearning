package cn.zju.leetcode;

import java.util.PriorityQueue;

public class code9_871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int ans = 0, cur = 0;
        for (int[] station : stations) {
            int next = station[0];
            int fuel = station[1];
            startFuel -= next - cur;
            while (!pq.isEmpty() && startFuel < 0) {
                startFuel += pq.poll();
                ans++;
            }
            if (startFuel < 0) return -1;
            cur = next;
            pq.offer(fuel);
        }
        startFuel -= target - cur;
        while (!pq.isEmpty() && startFuel < 0) {
            startFuel += pq.poll();
            ans++;
        }
        if (startFuel < 0) return -1;
        return ans;
    }
}
