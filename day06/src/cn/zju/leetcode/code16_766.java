package cn.zju.leetcode;

public class code16_766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int x = i, y = 0;
            int temp = matrix[x++][y++];
            while (x < m && y < n) {
                if(temp != matrix[x++][y++]) {
                    return false;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int x = 0, y = i;
            int temp = matrix[x++][y++];
            while (x < m && y < n) {
                if(temp != matrix[x++][y++]) {
                    return false;
                }
            }
        }
        return true;
    }
}
