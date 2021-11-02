package cn.zju.leetcode;

import java.util.HashSet;
import java.util.Set;

public class code5_575 {
    public static int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int c : candyType) {
            set.add(c);
        }
        return Math.min(n / 2, set.size());
    }

}
