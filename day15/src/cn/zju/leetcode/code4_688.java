package cn.zju.leetcode;

public class code4_688 {
    public double knightProbability1(int n, int k, int row, int column) {
        return process(n, 0, k, row, column) / Math.pow(8, k);
    }
    public int process(int n, int i, int k, int row, int column) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (i == k) {
            return 1;
        }
        int ans = 0;
        ans += process(n, i + 1, k, row + 2, column + 1)
                + process(n, i + 1, k, row - 2, column + 1)
                + process(n, i + 1, k, row + 2, column - 1)
                + process(n, i + 1, k, row - 2, column - 1)
                + process(n, i + 1, k, row + 1, column + 2)
                + process(n, i + 1, k, row - 1, column + 2)
                + process(n, i + 1, k, row + 1, column - 2)
                + process(n, i + 1, k, row - 1, column - 2);
        return ans;
    }

    public double knightProbability2(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][K] = 1;
            }
        }
        for (int z = K - 1; z >= 0; z--) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j][z] += isVaild(i + 2, j + 1, N) ? dp[i + 2][j + 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 2, j - 1, N) ? dp[i + 2][j - 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 2, j + 1, N) ? dp[i - 2][j + 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 2, j - 1, N) ? dp[i - 2][j - 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 1, j + 2, N) ? dp[i + 1][j + 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 1, j - 2, N) ? dp[i + 1][j - 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 1, j + 2, N) ? dp[i - 1][j + 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 1, j - 2, N) ? dp[i - 1][j - 2][z + 1] : 0;
                }
            }
        }
        return dp[r][c][0] / Math.pow(8, K);
    }

    public boolean isVaild(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    public double knightProbability3(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = 1;
            }
        }
        for (int z = K - 1; z >= 0; z--) {
            double[][] dp2 = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < 8; k++) {
                        int cr = i + dr[k];
                        int cc = j + dc[k];
                        if (isVaild(cr, cc, N)) {
                            dp2[i][j] += dp[cr][cc];
                        }
                    }
                }
            }
            dp = dp2;
        }
        return dp[r][c] / Math.pow(8, K);
    }
}
