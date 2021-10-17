package week5_2021_10_16;

import java.util.*;

public class code1_5888 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        int n = graph.size();
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (distances[node[0]] == -1) {
                distances[node[0]] = node[1];
                for (int to : graph.get(node[0])) {
                    if (distances[to] == -1) {
                        pq.add(new int[]{to, node[1] + 1});
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int dis = distances[i];
            int send = patience[i];
            ans = Math.max(ans, 2 * dis + ((2 * dis - 1) / send) * send);
        }
        return ans + 1;
    }
}
