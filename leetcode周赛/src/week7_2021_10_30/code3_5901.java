package week7_2021_10_30;

import java.util.HashMap;
import java.util.Map;

/**
 * 有一个 8 x 8 的棋盘，它包含 n 个棋子（棋子包括车，后和象三种）。
 * 给你一个长度为 n 的字符串数组 pieces ，其中 pieces[i] 表示第 i 个棋子的类型（车，后或象）。
 * 除此以外，还给你一个二维整数数组 positions ，其中positions[i] = [ri, ci] 表示第 i 个棋子现在在棋盘上的位置为 (ri, ci) ，棋盘下标从 1 开始。
 *
 * 棋盘上每个棋子都可以移动 至多一次 。每个棋子的移动中，首先选择移动的 方向 ，然后选择 移动的步数 ，同时你要确保移动过程中棋子不能移到棋盘以外的地方。
 * 棋子需按照以下规则移动：
 *
 * 车可以 水平或者竖直 从 (r, c) 沿着方向 (r+1, c)，(r-1, c)，(r, c+1) 或者 (r, c-1) 移动。
 * 后可以 水平竖直或者斜对角 从 (r, c) 沿着方向 (r+1, c)，(r-1, c)，(r, c+1)，(r, c-1)，(r+1, c+1)，(r+1, c-1)，(r-1, c+1)，(r-1, c-1) 移动。
 * 象可以 斜对角 从 (r, c) 沿着方向 (r+1, c+1)，(r+1, c-1)，(r-1, c+1)，(r-1, c-1) 移动。
 * 移动组合 包含所有棋子的 移动 。每一秒，每个棋子都沿着它们选择的方向往前移动 一步 ，直到它们到达目标位置。
 * 所有棋子从时刻 0 开始移动。如果在某个时刻，两个或者更多棋子占据了同一个格子，那么这个移动组合 不有效 。
 *
 * 请你返回 有效 移动组合的数目。
 *
 * 注意：
 *
 * 初始时，不会有两个棋子 在 同一个位置 。
 * 有可能在一个移动组合中，有棋子不移动。
 * 如果两个棋子 直接相邻 且两个棋子下一秒要互相占据对方的位置，可以将它们在同一秒内 交换位置 。
 */
public class code3_5901 {
    int[][] dirx = new int[3][];
    int[][] diry = new int[3][];
    int ans = 0;
    Map<String, Integer> map = new HashMap<>();
    public int countCombinations(String[] pieces, int[][] positions) {
        dirx[0] = new int[]{-1, 1, 0, 0};
        dirx[1] = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
        dirx[2] = new int[]{-1, 1, -1, 1};
        diry[0] = new int[]{0, 0, -1, 1};
        diry[1] = new int[]{0, 0, -1, 1, -1, 1, 1, -1};
        diry[2] = new int[]{-1, 1, 1, -1};
        map.put("rook", 0);
        map.put("queen", 1);
        map.put("bishop", 2);
        int n = pieces.length;
        int[][] status = new int[n][5];
        for (int i = 0; i < n; i++) {
            status[i][0] = positions[i][0] - 1;
            status[i][1] = positions[i][1] - 1;
            status[i][4] = map.get(pieces[i]);
        }
        dfs(0, n, status);
        return ans;
    }

    public void dfs(int cur, int n, int[][] status) {
        if (cur == n) {
            ans += check(status);
            return;
        }
        int tp = status[cur][4];
        for (int i = 0; i < dirx[tp].length; i++) {
            for (int j = 1; j < 8; j++) {
                int nx = status[cur][0] + dirx[tp][i] * j;
                int ny = status[cur][1] + diry[tp][i] * j;
                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                    status[cur][2] = i;
                    status[cur][3] = j;
                    dfs(cur + 1, n, status);
                }
            }
        }
        status[cur][3] = 0;
        dfs(cur + 1, n, status);
    }
    public int getPos(int x, int y) {
        return 8 * x + y;
    }
    public int check(int[][] status) {
        for (int i = 1; i < 8; i++) {
            int[] board = new int[64];
            for (int[] sta : status) {
                int step = Math.min(i, sta[3]);
                int nx = step * dirx[sta[4]][sta[2]] + sta[0];
                int ny = step * diry[sta[4]][sta[2]] + sta[1];
                if (++board[getPos(nx, ny)] > 1) return 0;
            }
        }
        return 1;
    }
}
