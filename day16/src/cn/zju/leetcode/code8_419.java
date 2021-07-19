package cn.zju.leetcode;

public class code8_419 {
    public int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    board = cleanBoard(board, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    public char[][] cleanBoard(char[][] board, int i, int j) {
        board[i][j] = '0';
        for (int col = j + 1; col < board[0].length; col++) {
            if (board[i][col] == 'X') {
                board[i][col] = '0';
            } else {
                break;
            }
        }
        for (int row = i + 1; row < board.length; row++) {
            if (board[row][j] == 'X') {
                board[row][j] = '0';
            } else {
                break;
            }
        }
        return board;
    }

}
