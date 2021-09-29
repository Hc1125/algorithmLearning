package cn.zju.leetcode;

import java.util.Arrays;

public class code3_517 {
    public int findMinMoves(int[] machines) {
        int sum = Arrays.stream(machines).sum();
        int size = machines.length;
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int num : machines) {
            num -= avg;
            leftSum += num;
            ans = Math.max(ans, Math.max(Math.abs(leftSum), num));
        }
        return ans;
    }
}
