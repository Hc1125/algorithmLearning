package cn.zju.zuochengyun.Practice3;

import java.util.TreeSet;

/**
 * 给定一个二维数组matrix，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子矩阵累加和
 */
public class code6_MaxSumofRectangleNoLargerThanK {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length, res = Integer.MIN_VALUE;
        TreeSet<Integer> sumSet = new TreeSet<>();
        for (int s = 0; s < row; s++) { // 开始行
            int[] colSum = new int[col];
            for (int e = s; e < row; e++) { // 结束行
                sumSet.add(0);
                int rowSum = 0;
                for (int c = 0; c < col; c++) {
                    colSum[c] += matrix[e][c];
                    rowSum += colSum[c];
                    Integer it = sumSet.ceiling(rowSum - k);
                    if (it != null) {
                        res = Math.max(res, rowSum - it);
                    }
                    sumSet.add(rowSum);
                }
                sumSet.clear();
            }
        }
        return res;
    }
}
