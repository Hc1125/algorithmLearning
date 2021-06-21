package cn.zju.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class code10_888 {
    public int[] fairCandySwap(int[] A, int[] B){
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int[] ans = new int[2];
        int delta = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();
        for (int i : A) {
            set.add(i);
        }
        for (int y : B) {
            int x = y + delta;
            if(set.contains(x)){
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}
