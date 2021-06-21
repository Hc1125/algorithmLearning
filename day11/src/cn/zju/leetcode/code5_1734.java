package cn.zju.leetcode;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class code5_1734 {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] perm = new int[n];
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        for (int i = 1; i < n  - 1; i += 2) {
            total ^= encoded[i];
        }
        perm[0] = total;
        for (int i = 1; i < n; i++) {
            perm[i] = perm[i - 1] ^ encoded[i - 1];
        }
        return perm;
    }
}
