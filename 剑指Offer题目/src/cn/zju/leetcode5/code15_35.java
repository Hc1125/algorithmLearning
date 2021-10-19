package cn.zju.leetcode5;

import java.util.Arrays;
import java.util.List;

public class code15_35 {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String time = timePoints.get(i);
            int h = (time.charAt(0) - '0') * 10 + time.charAt(1) - '0';
            int m = (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
            times[i] = h * 60 + m;
        }
        Arrays.sort(times);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < times.length - 1; i++) {
            ans = Math.min(ans, times[i + 1] - times[i]);
            if (ans == 0) {
                return 0;
            }
        }
        ans = Math.min(ans, 24 * 60 - times[n - 1] + times[0]);
        return ans;
    }
}
