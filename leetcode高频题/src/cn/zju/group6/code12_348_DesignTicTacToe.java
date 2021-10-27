package cn.zju.group6;

/**
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 *
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 *
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 *   1 . 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 *
 *   2 . 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 *
 *   3 . 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 */
public class code12_348_DesignTicTacToe {
    class TicTacToe {
        private int[][] rows;
        private int[][] cols;
        private int[] leftUp;
        private int[] rightUp;
        private boolean[][] matrix;
        private int N;

        public TicTacToe(int n) {
            rows = new int[n][3]; //0 1 2
            cols = new int[n][3];
            leftUp = new int[3]; // 左对角线 1 2
            rightUp = new int[3]; // 右对角线 1 2
            matrix = new boolean[n][n];
            N = n;
        }

        public int move(int row, int col, int player) {
            if (matrix[row][col]) {
                return 0;
            }
            matrix[row][col] = true;
            rows[row][player]++;
            cols[col][player]++;
            if (row == col) {
                leftUp[player]++;
            }
            if (row + col == N - 1) {
                rightUp[player]++;
            }
            if (rows[row][player] == N || cols[col][player] == N || leftUp[player] == N || rightUp[player] == N) {
                return player;
            }
            return 0;
        }

    }
}
