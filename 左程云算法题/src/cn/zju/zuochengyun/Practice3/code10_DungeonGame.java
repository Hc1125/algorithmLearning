package cn.zju.zuochengyun.Practice3;

/**
 * 给定一个二维数组map，含义是一张地图，例如，如下矩阵：
 * -2 -3 3
 * -5 -10 1
 * 0 30 -5
 * 游戏规则如下：
 * 骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主
 * 地图中每个位置的值代表骑士要遭遇的事情
 * 如果是负数，说明此处有怪兽，要让骑士损失血量
 * 如果是非负数，代表此处有血瓶，能让骑士回血
 * 骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1
 * 为了保证骑士能见到公主，初始血量至少是多少？根据map，返回至少的初始血量
 */
public class code10_DungeonGame {
    public static int minHp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 1;
        }
        return process(matrix, matrix.length, matrix[0].length,0, 0);
    }
    // 来到了matrix[row][col]，还没登上去，到达右下角，返回至少的初始血量
    public static int process(int[][] matrix, int N, int M, int row, int col) {
        if(row == N - 1 && col == M - 1) { // 已经达到右下角了
            return matrix[N-1][M-1] < 0 ? (-matrix[N-1][M-1] + 1) : 1;
        }
        if(row == N - 1) {
            int rightNeed = process(matrix, N, M, row, col + 1);
            if(matrix[row][col] < 0) {
                return -matrix[row][col] + rightNeed;
            }else if(matrix[row][col] >= rightNeed) {
                return 1;
            }else {
                return rightNeed - matrix[row][col];
            }
        }
        if(col == M - 1) {
            int downNeed = process(matrix, N, M, row + 1, col);
            if(matrix[row][col] < 0) {
                return -matrix[row][col] + downNeed;
            }else if(matrix[row][col] >= downNeed) {
                return 1;
            }else {
                return downNeed - matrix[row][col];
            }
        }
        int minNextNeed = Math.min(process(matrix, N, M, row, col + 1), process(matrix, N, M, row + 1, col));
        if(matrix[row][col] < 0) {
            return -matrix[row][col] + minNextNeed;
        }else if(matrix[row][col] >= minNextNeed) {
            return 1;
        }else {
            return minNextNeed - matrix[row][col];
        }
    }

    public static int minHp1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 1;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row--][col--];
        dp[row][col] = matrix[row][col] > 0 ? 1 : -matrix[row][col] + 1;
        for (int j = col - 1; j >= 0; j--) {
            dp[row][j] = Math.max(dp[row][j + 1] - matrix[row][j], 1);
        }
        int right = 0;
        int down = 0;
        for (int i = row - 1; i >= 0; i--) {
            dp[i][col] = Math.max(dp[i + 1][col] - matrix[i][col], 1);
            for (int j = col - 1; j >= 0; j--) {
                right = Math.max(dp[i][j + 1] - matrix[i][j], 1);
                down = Math.max(dp[i + 1][j] - matrix[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    public static int minHp2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 1;
        }
        int more = Math.max(matrix.length, matrix[0].length);
        int less = Math.min(matrix.length, matrix[0].length);
        boolean rowmore = more == matrix.length;
        int[] dp = new int[less];
        int tmp = matrix[matrix.length - 1][matrix[0].length - 1];
        dp[less - 1] = tmp > 0 ? 1 : -tmp + 1;
        int row = 0;
        int col = 0;
        for (int j = less - 2; j >= 0; j--) {
            row = rowmore ? more - 1 : j;
            col = rowmore ? j : more - 1;
            dp[j] = Math.max(dp[j + 1] - matrix[row][col], 1);
        }
        int choosen1 = 0;
        int choosen2 = 0;
        for (int i = more - 2; i >= 0; i--) {
            row = rowmore ? i : less - 1;
            col = rowmore ? less - 1 : i;
            dp[less - 1] = Math.max(dp[less - 1] - matrix[row][col], 1);
            for (int j = less - 2; j >= 0; j--) {
                row = rowmore ? i : j;
                col = rowmore ? j : i;
                choosen1 = Math.max(dp[j] - matrix[row][col], 1);
                choosen2 = Math.max(dp[j + 1] - matrix[row][col], 1);
                dp[j] = Math.min(choosen1, choosen2);
            }
        }
        return dp[0];
    }
}
