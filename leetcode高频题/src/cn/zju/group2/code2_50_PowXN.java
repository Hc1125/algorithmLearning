package cn.zju.group2;

public class code2_50_PowXN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double t = x;
        double ans = 1D;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            t = t * t;
            pow >>= 1;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        return n < 0 ? (1D / ans) : ans;
    }
}
