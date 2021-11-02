package cn.zju.leetcode;

import java.util.PriorityQueue;

public class code6_407 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int ans = 0;
        int[] dir = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node[0] / n + dir[i];
                int ny = node[0] % n + dir[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (node[1] > heightMap[nx][ny]) {
                        ans += node[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], node[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return ans;
    }


}
