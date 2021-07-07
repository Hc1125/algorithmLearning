package cn.zju.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class code11_1711 {
    public int countPairs(int[] deliciousness) {
        int max = 2 * Arrays.stream(deliciousness).max().getAsInt();
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deliciousness) {
            for (int i = 1; i <= max; i <<= 1) {
                int count = map.getOrDefault(i - num, 0);
                ans = (ans + count) % 1000000007;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return ans;
    }
}
