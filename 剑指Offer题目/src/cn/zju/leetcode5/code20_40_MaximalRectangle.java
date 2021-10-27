package cn.zju.leetcode5;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class code20_40_MaximalRectangle {
    public int maximalRectangle(String[] matrix) {
        int m = matrix.length;
        if (m == 0) {
           return 0;
        }
        int n = matrix[0].length();
        // 每个点左边有多少连续的1
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i].charAt(j) == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        int ans = 0;
        // 每一列是倒着的柱状图，求出每个点对应上限和下限
        for (int j = 0; j < n; j++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Arrays.fill(down, m);
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    down[stack.pop()] = i;
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i]  - 1;
                int area = height * left[i][j];
                ans = Math.max(area, ans);
            }
        }
        return ans;
    }
}
