package cn.zju.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code14_554 {
    public int leastBricks(List<List<Integer>> wall) {
        int n = wall.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = wall.get(i);
            int edge = 0;
            for (int j = 0; j < list.size() - 1; j++) {
                edge += list.get(j);
                map.put(edge, map.getOrDefault(edge, 0) + 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return n - max;
    }
}
