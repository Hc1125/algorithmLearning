package cn.zju.leetcode;

import java.util.*;

public class code13_815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = map.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                map.put(site, list);
            }
        }
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int bus : map.getOrDefault(source, new ArrayList<>())) {
            dis[bus] = 1;
            queue.offer(bus);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    queue.offer(y);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int bus : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[bus] != -1) {
                ans = Math.min(ans, dis[bus]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
