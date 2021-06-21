package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class code11_354 {
    public int maxEnvelope(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0 || n == 1) {
            return n;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
    public int maxEnvelope1(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0 || n == 1) {
            return n;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] f = new int[n + 1];
        int len = 1;
        f[len] = envelopes[0][1];
        for (int i = 1; i < n; i++) {
            int num = envelopes[i][1];
            if (num > f[len]) {
                f[++len] = num;
            } else {
                int l = 1, r = len, pos = 0;
                while (l < r) {
                    int mid = l + ((r - l) >> 1);
                    if (num > f[mid]) {
                        pos = mid;
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[pos + 1] = num;
            }
        }
        return len;
    }
}
