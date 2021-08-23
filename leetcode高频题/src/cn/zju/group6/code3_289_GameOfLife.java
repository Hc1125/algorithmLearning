package cn.zju.group6;

public class code3_289_GameOfLife {
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int neighbors = neighbor(board, i, j);
                if (neighbors == 3 || (neighbors == 2 && board[i][j] == 1)) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    public int neighbor(int[][] board, int i, int j) {
        return f(board, i + 1, j) +
                f(board, i - 1, j) +
                f(board, i + 1, j + 1) +
                f(board, i + 1, j - 1) +
                f(board, i, j + 1) +
                f(board, i - 1, j - 1) +
                f(board, i - 1, j + 1) +
                f(board, i, j - 1);
    }

    public int f(int[][] b, int i, int j) {
        return (i >= 0 && i < b.length && j >= 0 && j < b[0].length && (b[i][j] & 1) == 1) ? 1 : 0;
    }
}
