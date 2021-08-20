package cn.zju.group5;

public class code11_279_PerfectSquares {
    // 数学定理，任何一个自然数可以被至多4个数字的完全平方数构成
    // 规律解
    // 规律一：个数不超过4
    // 规律二：出现一个的时候，显而易见
    // 规律三：任何数 % 8 == 7，一定是4个
    // 规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7,一定是4个
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

    // 数学解
    // 1)四平方和定理
    // 2)任何数消掉4的因子，结论不变
    public int numSquares2(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int a = 0; a * a <= n; ++a) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a > 0 && b > 0) ? 2 : 1;
            }
        }
        return 3;
    }
}
