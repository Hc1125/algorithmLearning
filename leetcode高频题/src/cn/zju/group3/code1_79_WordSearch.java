package cn.zju.group3;



public class code1_79_WordSearch {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean process(char[][] board, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }
        if (board[i][j] != w[k]) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = 0;
        boolean ans = process(board, i + 1, j, w, k + 1) || process(board, i - 1, j, w, k + 1)
                || process(board, i, j + 1, w, k + 1) || process(board, i, j - 1, w, k + 1);
        board[i][j] = tmp;
        return ans;
    }
}
