package cn.zju.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code15_130 {
    int n, m;
    public void solve(char[][] board) {
        n = board.length;
        if(n == 0){
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i , m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void dfs(char[][] board, int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public void solve1(char[][] board) {
        n = board.length;
        if(n == 0){
            return;
        }
        m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(board[i][0] == 'O') {
                queue.offer(new int[]{i,0});
            }
            if(board[i][m - 1] == 'O') {
                queue.offer(new int[]{i,m - 1});
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if(board[0][i] == 'O') {
                queue.offer(new int[]{0,i});
            }
            if(board[n - 1][i] == 'O') {
                queue.offer(new int[]{n - 1,i});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if(mx < 0 || mx >= n || my < 0 || my >= m || board[mx][my] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, my});
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

}
