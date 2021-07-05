package cn.zju.zuochengyun.Practice3;
/**
 * 原题：
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
 *
 * 升级版：
 * 骑士能上下左右走，并且给定起点和终点
 * 矩阵大小不超过200
 */
public class code10_DugeonGame_Plus {



    static int[][] dp;
    public static int minimumInitHealth2(int[][] rooms, int[] startPoint, int[] endPoint) {
        int row = rooms.length;
        int col = rooms[0].length;
        boolean[][] isTrue = new boolean[row][col];
        dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = -1;
            }
        }
        return process(rooms, endPoint[0], endPoint[1], startPoint[0], startPoint[1], row, col, isTrue);
    }

    public static int process(int[][] rooms, int endX, int endY, int i, int j, int row, int col, boolean[][] isTrue) {
        if (i == endX && j == endY) {
            return rooms[endX][endY] < 0 ? (-rooms[endX][endY] + 1) : 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        isTrue[i][j] = true;
        int ans = Integer.MAX_VALUE;
        if (verify(i + 1, j, row, col) && !isTrue[i + 1][j]) {
            ans = Math.min(ans, process(rooms, endX, endY, i + 1, j, row, col, isTrue));
        }
        // 如果在上边界或者下边界则肯定不能往左走,重要优化
        if (verify(i - 1, j, row, col) && !isTrue[i - 1][j] && j > 0 && j < col - 1) {
            ans = Math.min(ans, process(rooms, endX, endY, i - 1, j, row, col, isTrue));
        }
        if (verify(i, j + 1, row, col) && !isTrue[i][j + 1]) {
            ans = Math.min(ans, process(rooms, endX, endY, i, j + 1, row, col, isTrue));
        }
        // 如果在左边界或者右边界则肯定不能往上走,重要优化
        if (verify(i, j - 1, row, col) && !isTrue[i][j - 1] && i > 0 && i < row - 1) {
            ans = Math.min(ans, process(rooms, endX, endY, i, j - 1, row, col, isTrue));
        }
        isTrue[i][j] = false;
        if (rooms[i][j] < 0) {
            dp[i][j] = -rooms[i][j] + ans;
        } else if (rooms[i][j] >= ans) {
            dp[i][j] = 1;
        } else {
            dp[i][j] = ans - rooms[i][j];
        }
        return dp[i][j];
    }

    public static boolean verify(int i, int j, int row, int col) {
        return i >= 0 && j >= 0 && i < row && j < col;
    }
}
