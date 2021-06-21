package cn.zju.zuochengyun.FibonacciProblem;

/**
 * 第一年农场有一只成熟的母牛A，往后的每年：
 * 1）每一只成熟的母牛都会生一只母牛
 * 2）每一只新出生的母牛都在出生的第三年成熟
 * 3）每一只母牛永远都不会死
 * 返回N年后牛的数量
 * 推出推导公式 ： F(N) = F(N - 1) + F(N - 3)
 * |F(N)  F(N - 1)  F(N - 2)| = |F(3)  F(2)  F(1)| * (  {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}} ^ (N - 3)  )
 *
 * |F(N)  F(N - 1)  F(N - 2) ... F(N - i)| = |i ... 3 2 1| * ( |i * i| ^ (N - i) )
 */
public class code2_CountCows {

    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }



    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        // res = 矩阵中的1
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}