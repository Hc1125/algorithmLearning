package cn.zju.zuochengyun.Recursion;

/**
 * N皇后问题是指在N * N的棋盘上要摆N个皇后
 * 要求任何两个皇后不同行、不同列、也不在同一个斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种方法
 */
public class code8_NQueens {
    /**
     * 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共线、不共列，不共斜线
     * 目前来到了第i行
     * record[0..i-1]表示之前的行，放了的皇后位置
     * n代表整体一共有多少行
     * 返回值是，摆完后的皇后，合理的摆法有多少种
     */
    public static int num1(int n) {
        int[] record = new int[n];
        return process1(0, record, n);
    }
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列 -> j
            // 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
            // 如果是，认为有效
            // 如果不是，认为无效
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }
    // record[0..i-1]你需要看，record[i...]不需要看
    // 返回i行皇后，放在了j列，是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { // 之前的某个k行的皇后
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit   划定了问题的规模
     * @param colLim  列的限制，1的位置不能放皇后，0的位置可以
     * @param leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
     * @param rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
     * @return
     */
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // 所有候选皇后的位置，都在pos上
        // colLim | leftDiaLim | rightDiaLim   ->  总限制
        // ~(colLim | leftDiaLim | rightDiaLim)  ->  左侧的一坨0干扰，右侧每一个1，可尝试
        int pos = limit & ( ~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            // 提取出pos中最右侧的1来，剩下的位置都是0；
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += process2(limit, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>>1);
        }
        return res;
    }
    public static void main(String[] args) {
        int n = 14;
        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
