package cn.zju.leetcode4;

public class code1 {
    public int divide(int a, int b) {
        if (a == 0) return 0;
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        long x = a > 0 ? a : add(~a, 1);
        long y = b > 0 ? b : add(~b, 1);
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            if ((x >> i) >= y) {
                ans |= (1 << i);
                x = sub(x, y << i);
            }
        }
        if ((a ^ b) >= 0) {
            return ans;
        } else {
            return (int) add(~ans, 1);
        }
    }
    public long add(long a, long b) {
        while (b != 0) {
            long tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }
        return a;
    }
    public long sub(long a, long b) {
        return add(a, add(~b, 1));
    }


}
