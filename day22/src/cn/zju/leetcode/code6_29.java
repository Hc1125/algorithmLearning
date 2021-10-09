package cn.zju.leetcode;

public class code6_29 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long a = dividend > 0 ? dividend : add(~dividend, 1);
        long b = divisor > 0 ? divisor : add(~divisor, 1);
        long ans = 0;
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {
                ans = add(ans, 1 << i);
                a = sub(a, b << i);
            }
        }
        if ((dividend ^ divisor) < 0) {
            return (int) add(~ans, 1);
        } else {
            return (int) ans;
        }
    }

    public long add(long a, long b) {
        do {
            long carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        } while (b != 0);
        return a;
    }

    public long sub(long a, long b) {
        return add(a, add(~b, 1));
    }
}
