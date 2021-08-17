package cn.zju.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class code13_1840 {
    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions == null || restrictions.length == 0) {
            return n - 1;
        }
        Arrays.sort(restrictions, Comparator.comparingInt(x -> x[0]));
        int len = restrictions.length;
        for (int i = len - 2; i >= 0; i--) {
            restrictions[i][1] = Math.min(restrictions[i][1],
                    Math.min(restrictions[i][0] - 1, restrictions[i + 1][1] + restrictions[i + 1][0] - restrictions[i][0]));
        }
        int maxHeight = (restrictions[0][1] + restrictions[0][0] - 1) >> 1;
        for (int i = 1; i < len; i++) {
            restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0]);
            // 根据数学关系获得两个限制中的最高高度
            maxHeight = Math.max(maxHeight, (restrictions[i - 1][1] + restrictions[i][1] + restrictions[i][0] - restrictions[i - 1][0]) >> 1);
        }
        maxHeight = Math.max(maxHeight, restrictions[len - 1][1] + n - restrictions[len - 1][0]);
        return maxHeight;
    }
}
