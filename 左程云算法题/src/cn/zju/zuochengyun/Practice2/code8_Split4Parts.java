package cn.zju.zuochengyun.Practice2;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要
 * 例如：
 * arr = [3,2,4,1,4,9,5,10,1,2,2]返回true
 * 三个切割点下标为2,5,7 切出的四个子数组为[3,2],[1,4],[5],[1,2,2]
 * 累加和都是5
 */
public class code8_Split4Parts {
    public static boolean camSplit(int[] arr) {
        int n = arr.length;
        if (arr == null ||n < 7) {
            return false;
        }
        int[] sum = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
            map.put(sum[i], i);
        }
        for (int i = 1; i <= n - 6; i++) {
            int extra = arr[i];
            int x = sum[i];
            if (map.containsKey(extra + 2 * x) && map.get(extra + 2 * x) <= n - 4) {
                extra += arr[map.get(extra + 2 * x)];
                if (map.containsKey(extra + 3 * x) && map.get(extra + 3 * x) <= n - 2) {
                    if (sum[n] - sum[map.get(extra + 3 * x) + 1] == x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
