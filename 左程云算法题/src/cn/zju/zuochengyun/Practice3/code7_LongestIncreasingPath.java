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
// https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
public class code7_LongestIncreasingPath {
    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process1(int[][] m, int i, int j) {
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process1(m, i - 1, j) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process1(m, i + 1, j) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process1(m, i, j - 1) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
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
    // 从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // (i,j)不越界
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process2(m, i - 1, j, dp) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process2(m, i + 1, j, dp) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process2(m, i, j - 1, dp) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process2(m, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }
}
