package cn.zju.leetcode;

public class code5_59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int num = 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            for (int i = top + 1; i <= bottom; i++) {
                res[i][right] = num++;
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    res[bottom][i] = num++;
                }
                for (int i = bottom - 1; i > top; i--) {
                    res[i][left] = num++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}
