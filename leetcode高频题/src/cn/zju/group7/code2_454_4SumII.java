package cn.zju.group7;

import java.util.HashMap;
import java.util.Map;

public class code2_454_4SumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int u : A) {
            for (int v : B) {
                map.put(u + v, map.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (map.containsKey(-u-v)) {
                    ans += map.get(-u-v);
                }
            }
        }
        return ans;
    }
}
