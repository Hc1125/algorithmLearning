package cn.zju.leetcode;

import java.util.*;

public class code10_773 {
    public int slidingPuzzle(int[][] board) {
        if (matrixToNum(board) == 123450) return 0;
        Set<Integer> set = new HashSet<>();
        Queue<int[][]> queue = new LinkedList<>();
        queue.add(board);
        set.add(matrixToNum(board));
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[][] matrix = queue.poll();
                for (int[][] nextMatrix : getNextMatrix(matrix)) {
                    if (!set.contains(matrixToNum(nextMatrix))) {
                        if (matrixToNum(nextMatrix) == 123450) {
                            return step;
                        }

                        set.add(matrixToNum(nextMatrix));
                        queue.add(nextMatrix);
                    }
                }
            }
        }
        return -1;
    }
    public LinkedList<int[][]> getNextMatrix(int[][] board) {
        LinkedList<int[][]> ans = new LinkedList<>();
        int i = 0, j = 0;
        for (int i1 = 0; i1 < board.length; i1++) {
            for (int j1 = 0; j1 < board[i1].length; j1++) {
                if (board[i1][j1] == 0) {
                    i = i1;
                    j = j1;
                }
            }
        }
        if (i + 1 < 2) {
            int[][] temp = copyArr(board);
            temp[i][j] = board[i + 1][j];
            temp[i + 1][j] = board[i][j];
            ans.add(temp);
        }
        if (i - 1 >= 0) {
            int[][] temp = copyArr(board);
            temp[i][j] = board[i - 1][j];
            temp[i - 1][j] = board[i][j];
            ans.add(temp);
        }
        if (j + 1 < 3) {
            int[][] temp = copyArr(board);
            temp[i][j] = board[i][j + 1];
            temp[i][j + 1] = board[i][j];
            ans.add(temp);
        }
        if (j - 1 >= 0) {
            int[][] temp = copyArr(board);
            temp[i][j] = board[i][j - 1];
            temp[i][j - 1] = board[i][j];
            ans.add(temp);
        }
        return ans;
    }
    public int[][] copyArr(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        return temp;
    }
    public int matrixToNum(int[][] matrix) {
        int ans = 0;
        for (int[] ints : matrix) {
            for (int i : ints) {
                ans *= 10;
                ans += i;
            }
        }
        return ans;
    }

}
