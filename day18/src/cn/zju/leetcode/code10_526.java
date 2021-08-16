package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code10_526 {
    public int countArrangement1(int n) {
        return process(n, 1, new HashSet<>());
    }
    public int process(int n, int i, Set<Integer> set) {
        if (i == n + 1) {
            return 1;
        }
        int ans = 0;
        for (int j = 1; i * j <= n; j++) {
            if (!set.contains(i * j)) {
                set.add(i * j);
                ans += process(n, i + 1, set);
                set.remove(i * j);
            }
        }
        for (int j = 1; j < i; j++) {
            if (i % j == 0) {
                if (!set.contains(j)) {
                    set.add(j);
                    ans += process(n, i + 1, set);
                    set.remove(j);
                }
            }
        }
        return ans;
    }
    public int countArrangement2(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        for (int mask = 1; mask < (1 << n); mask++) {
            int num = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0 && (num % (i + 1) == 0 || (i + 1) % num == 0)) {
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
