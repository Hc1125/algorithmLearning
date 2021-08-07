package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class code20_847 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            for (int i : graph[u]) {
                int maskI = mask | (1 << i);
                if (!seen[i][maskI]) {
                    queue.offer(new int[]{i, maskI, dist + 1});
                    seen[i][maskI] = true;
                }
            }
        }
        return ans;
    }
}
