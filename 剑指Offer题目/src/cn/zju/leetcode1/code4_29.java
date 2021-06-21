package cn.zju.leetcode1;

public class code4_29 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        int index = 0;
        int beginx = 0, beginy = 0, endx = m - 1, endy = n - 1;
        while (beginx <= endx && beginy <= endy) {
            int x = beginx, y = beginy;
            while (y <= endy) {
                ans[index++] = matrix[x][y++];
            }
            y--;
            x++;
            while (x <= endx) {
                ans[index++] = matrix[x++][y];
            }
            if (beginx < endx && beginy < endy) {
                x = endx;
                y = endy - 1;
                while (y > beginy) {
                    ans[index++] = matrix[x][y--];
                }
                while (x > beginx) {
                    ans[index++] = matrix[x--][y];
                }
            }
            beginx++;
            beginy++;
            endx--;
            endy--;
        }
        return ans;
    }
}

