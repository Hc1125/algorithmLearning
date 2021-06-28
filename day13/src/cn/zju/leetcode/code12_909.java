package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code12_909 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = p[0] + i;
                if (next > n * n) {
                    break;
                }
                int[] rc = id2rc(next, n);
                if (board[rc[0]][rc[1]] != -1) {
                    next = board[rc[0]][rc[1]];
                }
                if (next == n * n) {
                    return p[1] + 1;
                }
                if (!vis[next]) {
                    vis[next] = true;
                    queue.offer(new int[]{next, p[1] + 1});
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}
