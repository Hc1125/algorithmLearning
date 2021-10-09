package cn.zju.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class code1_352 {
    class SummaryRanges {
        TreeMap<Integer, Integer> treeMap;
        public SummaryRanges() {
            treeMap = new TreeMap<>();
        }

        public void addNum(int val) {
            Map.Entry<Integer, Integer> limit0 = treeMap.floorEntry(val);
            Map.Entry<Integer, Integer> limit1 = treeMap.ceilingEntry(val + 1);
            if (limit0 != null && limit0.getKey() <= val && limit0.getValue() >= val) {
                return;
            }
            boolean left = limit0 != null && limit0.getValue() + 1 == val;
            boolean right = limit1 != null && limit1.getKey() - 1 == val;
            if (left && right) {
                int l = limit0.getKey(), r = limit1.getValue();
                treeMap.remove(limit0.getKey());
                treeMap.remove(limit1.getKey());
                treeMap.put(l, r);
            } else if (left) {
                treeMap.put(limit0.getKey(), val);
            } else if (right) {
                int r = limit1.getValue();
                treeMap.put(val, r);
                treeMap.remove(limit1.getKey());
            } else {
                treeMap.put(val, val);
            }
        }

        public int[][] getIntervals() {
            int size = treeMap.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                ans[index][0] = entry.getKey();
                ans[index++][1] = entry.getValue();
            }
            return ans;
        }
    }
}
