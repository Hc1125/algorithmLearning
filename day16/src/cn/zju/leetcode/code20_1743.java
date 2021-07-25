package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code20_1743 {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<>());
            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }
        int n = adjacentPairs.length + 1;
        int[] ans = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans[0] = entry.getKey();
                break;
            }
        }
        ans[1] = map.get(ans[0]).get(0);
        for (int i = 2; i < n; i++) {
            List<Integer> list = map.get(ans[i - 1]);
            ans[i] = ans[i - 2] == list.get(0) ? list.get(1) : list.get(0);
        }
        return ans;
    }
}
