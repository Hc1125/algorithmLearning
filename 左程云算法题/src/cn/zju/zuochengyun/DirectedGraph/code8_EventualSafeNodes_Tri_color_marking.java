package cn.zju.zuochengyun.DirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 三色标记法

/**
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 *
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 *
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 *
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
 */
public class code8_EventualSafeNodes_Tri_color_marking {
    public List<Integer> eventualSafeNodes1(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }
    public boolean safe(int[][] graph, int[] color, int i) {
        if (color[i] != 0) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (int j : graph[i]) {
            if (!safe(graph, color, j)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> rg = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rg.add(new ArrayList<>());
        }
        int[] inDeg = new int[n];
        for (int i = 0; i < graph.length; i++) {
            for (int j : graph[i]) {
                rg.get(j).add(i);
            }
            inDeg[i] = graph[i].length;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDeg.length; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int i : rg.get(y)) {
                if (--inDeg[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < inDeg.length; i++) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
