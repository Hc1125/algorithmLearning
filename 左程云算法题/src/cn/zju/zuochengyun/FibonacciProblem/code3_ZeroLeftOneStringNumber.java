package cn.zju.zuochengyun.FibonacciProblem;

public class code3_ZeroLeftOneStringNumber {
    /**
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串，如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
     * 返回有多少达标的字符串
     *
     *
     *
     * 以F（N）代表还有N个数字要填（以最后一个数字为1表示），但第 N + 1 个数字一定是1的情况，有几种达标的字符串
     * 这样子，例如当字符串长度为9应该调F（8），如果第一位是0，必然没有达标的字符串
     * 所以当考虑第八位如果第八位为1即等于F（7），如果第八位为0则不满足F（7）条件
     * 所以第7位必须为1则等于F（6）      ---------> f(9) =  F(8) = F(7) + F(6)
     * 推出推导公式 f(N) = F(N - 1) = F(N - 2) + F(N - 3)  F(1) = 1, F(2) = 2
     * 套用斐波那契数列求解方法
     */
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }






    public static int fi(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = { { 1, 1 },
                { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }




    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

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

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
