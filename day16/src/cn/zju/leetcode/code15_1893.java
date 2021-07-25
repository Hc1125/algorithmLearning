package cn.zju.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class code15_1893 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }
        int cur = 0;
        for (int i = 1; i <= 50; i++) {
            cur += diff[i];
            if (i >= left && i <= right && cur < 0) {
                return false;
            }
        }
        return true;
    }
}
