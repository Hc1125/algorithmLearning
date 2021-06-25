package cn.zju.leetcode;

import java.util.HashMap;
import java.util.Map;

public class code8_149 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int same = 0;
            int sameX = 0;
            int sameY = 0;
            if (ans >= n - i || ans > n / 2) {
                break;
            }
            for (int j = i + 1; j < n; j++) {
                if (points[i] == points[j]) {
                    same++;
                } else if (points[i][0] == points[j][0]) {
                    sameX++;
                } else if (points[i][1] == points[j][1]){
                    sameY++;
                } else {
                    int distanceX = points[j][0] - points[i][0];
                    int distanceY = points[j][1] - points[i][1];
                    int base = gcd(distanceX, distanceY);
                    distanceX /= base;
                    distanceY /= base;
                    StringBuffer stringBuffer = new StringBuffer().append(distanceX).append("_").append(distanceY);
                    String s = stringBuffer.toString();
                    map.put(s, map.getOrDefault(s, 0) + 1);
                }
            }
            int max = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                max = Math.max(max, entry.getValue());
            }
            max = Math.max(max, Math.max(sameX, sameY));
            ans = Math.max(max + same + 1, ans);
        }
        return ans;
    }

    public int gcd(int m, int n) {
        return n == 0 ? m : (gcd(n, m % n));
    }
}
