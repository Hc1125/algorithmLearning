package cn.zju.leetcode;

public class code10_566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if(m * n != r * c || (m == r && n == c)) {
            return nums;
        }
        int[][] ret = new int[r][c];
        int a = 0, b = 0;
        /* for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[a][b++] = nums[i][j];
                if(b == c) {
                    a++;
                    b = 0;
                }
            }
        }*/
        for (int x = 0; x < m * n; ++x) {
            ret[x / c][x % c] = nums[x / n][x % n];
        }
        return ret;
    }
}
