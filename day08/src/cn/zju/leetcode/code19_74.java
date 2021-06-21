package cn.zju.leetcode;

public class code19_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && target > matrix[i][j]) {
            i++;
        }
        if (i == m) {
            return false;
        }
        while (j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else {
                j--;
            }
        }
        return false;
    }
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
