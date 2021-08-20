package cn.zju.group2;

public class code4_62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int part = n > m ? n - 1 : m - 1;
        int all = m + n - 2;
        long o1 = 1;
        long o2 = 1;
        for (int i = part + 1, j = 1; i <= all; i++, j++) {
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1, o2);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int)o1;
    }
    public long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
