package cn.zju.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code1_797 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.put(i, new ArrayList<>());
            for (int to : graph[i]) {
                map.get(i).add(to);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        process(0, graph.length - 1, list, ans, map);
        return ans;
    }

    public void process(int cur, int end, List<Integer> list, List<List<Integer>> ans, Map<Integer, List<Integer>> map) {
        if (cur == end) {
            ans.add(new ArrayList<>(list));
            return;
        }
        List<Integer> toList = map.get(cur);
        for (int to : toList) {
            list.add(to);
            process(to, end, list, ans, map);
            list.remove(list.size() - 1);
        }
    }


}
