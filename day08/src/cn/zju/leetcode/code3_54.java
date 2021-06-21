package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.List;

public class code3_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new LinkedList<>();
        int i = 0, j = 0;
        int index = 0;
        int circle = m < n ? (m + 1) / 2 : (n + 1) / 2;
        while (i < circle && j < circle) {
            while (j < n - 1 - index) {
                ans.add(matrix[i][j++]);
            }
            while (i < m - 1 - index) {
                ans.add(matrix[i++][j]);
            }
            if (i > index && j > index) {
                while (j > index) {
                    ans.add(matrix[i][j--]);
                }
                while (i > index) {
                    ans.add(matrix[i--][j]);
                }
            } else {
                ans.add(matrix[i][j]);
            }
            if (!ans.contains(matrix[index][index])) {
                ans.add(matrix[index][index]);
            }
            index++;
            i = index;
            j = index;
        }
        return ans;
    }
    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> order = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
