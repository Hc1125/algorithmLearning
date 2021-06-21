package cn.zju.leetcode2;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class code10_57_II {
    public int[][] findContinuousSequence(int target) {
        if (target == 1) {
            return new int[][]{{1}};
        }
        if (target == 2) {
            return new int[][]{};
        }
        List<int[]> vec = new ArrayList<>();
        for (int l = 1, r = 2; l < r; ) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; i++) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

}
