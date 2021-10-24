package cn.zju.group15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class code7_886_PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Map<Integer, Integer> color = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            if (!color.containsKey(i) && !dfs(i, 0, color, graph))
                return false;
        }
        return true;
    }

    public boolean dfs(int node, int c, Map<Integer, Integer> color, ArrayList<Integer>[] graph) {
        if (color.containsKey(node)) {
            return color.get(node) == c;
        }
        color.put(node, c);
        for (int to : graph[node]) {
            if (!dfs(to, c ^ 1, color, graph)) {
                return false;
            }
        }
        return true;
    }
}
