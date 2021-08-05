package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class code19_802_Tri_color_marking {
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
