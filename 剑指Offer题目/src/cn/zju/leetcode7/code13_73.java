package cn.zju.leetcode7;

import java.util.Arrays;

public class code13_73 {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (count(piles, m, h)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    public boolean count(int[] piles, int x, int h) {
        int ans = 0;
        for (int pile : piles) {
            ans += (pile + x - 1) / x;
        }
        return ans <= h;
    }
}
