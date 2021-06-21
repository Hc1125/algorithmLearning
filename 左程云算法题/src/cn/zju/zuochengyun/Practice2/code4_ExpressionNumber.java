package cn.zju.zuochengyun.Practice2;

public class code4_ExpressionNumber {
    /**
     * 给定一个只有0、1、&、|、^五种字符串组成的字符串express
     * 再给定一个布尔值desired。返回express能有多少种组合方式，可以达到desired的结果
     * express = "1^0|0|1", desired = false
     * 1^((0|0)|1) 和 1^(0|(0|1))的组合可以得到false，返回2，无组合返回0
     */
    public static boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < exp.length; i += 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i += 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }

    public static int num1(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        return f(exp, desired, 0, exp.length - 1);
    }

    /**
     * exp[L..R]返回期待为desired的方法数
     * 潜台词：L R必须是偶数位置
     */
    public static int f(char[] str, boolean desired, int L, int R) {
        if (L == R) {
            if (str[L] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }

        int res = 0;
        if (desired) {
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '|':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        break;
                }
            }
        } else {
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '|':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }

    public static int dpLive(String express, boolean desired) {
        char[] str = express.toCharArray();
        int N = str.length;
        int[][] tMap = new int[N][N];
        int[][] fMap = new int[N][N];
        for (int i = 0; i < N; i += 2) {
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }
        for (int row = N - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < N; col += 2) {
                for (int i = row + 1; i < col; i += 2) {
                    switch (str[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            break;
                    }
                    switch (str[i]) {
                        case '&':
                            tMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                    }
                }
            }
        }
        return desired ? tMap[0][N - 1] : fMap[0][N - 1];
    }
}
