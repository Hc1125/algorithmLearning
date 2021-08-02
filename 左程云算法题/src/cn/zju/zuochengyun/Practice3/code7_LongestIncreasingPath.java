package cn.zju.zuochengyun.Practice3;

/**
 * 给定一个二维数组matrix，可以从任何位置出发，每一步可以走向上下左右四个方向。返回最大递增链的长度
 * 例子：
 * matrix =
 * 5 4 3
 * 3 1 2
 * 2 1 3
 * 从最中心的1出发，是可以走出12345的链，而且这是最长的递增链。所以返回长度5
 */
public class code7_LongestIncreasingPath {
    public static int maxPath1(int[][] matrix) {
        int ans = Integer.MIN_VALUE;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                ans = Math.max(ans, process(matrix, row, col));
            }
        }
        return ans;
    }

    public static int process(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return -1;
        }
        int next1 = 0;
        int next2 = 0;
        int next3 = 0;
        int next4 = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next1 = process(matrix, i - 1, j);
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            next2 = process(matrix, i + 1, j);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next3 = process(matrix, i, j - 1);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            next4 = process(matrix, i, j + 1);
        }
        return 1 + Math.max(Math.max(next1, next2), Math.max(next3, next4));
    }




    public static int maxPath2(int[][] matrix) {
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                ans = Math.max(ans, process(matrix, row, col, dp));
            }
        }
        return ans;
    }

    /**
     * 记忆化搜索
     * 假设在matrix中，从i行，第j列出发，能走出的最长递增路径，返回
     * dp[i][j] == 0 process(i,j)之前没遇到过的
     * dp[i][j] != 0 process(i,j)之前已经算过了
     *
     */
    public static int process(int[][] matrix, int i, int j, int[][] dp) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return -1;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int next1 = 0;
        int next2 = 0;
        int next3 = 0;
        int next4 = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next1 = process(matrix, i - 1, j, dp);
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            next2 = process(matrix, i + 1, j, dp);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next3 = process(matrix, i, j - 1, dp);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            next4 = process(matrix, i, j + 1, dp);
        }
        int ans = 1 + Math.max(Math.max(next1, next2), Math.max(next3, next4));
        dp[i][j] = ans;
        return ans;
    }




}
