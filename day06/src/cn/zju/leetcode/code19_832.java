package cn.zju.leetcode;

public class code19_832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while(j <= n - 1 - j) {
                int temp = A[i][j];
                A[i][j] = 1 - A[i][n - 1 - j];
                A[i][n - 1 - j] = 1 - temp;
                j++;
            }
        }
        return A;
    }
}
