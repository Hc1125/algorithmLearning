package cn.zju.leetcode;

public class code18_mianshi17_01 {
    public int add(int a, int b) {
        int m = a ^ b;
        int n = (a & b) << 1;
        while (n != 0) {
            int temp = m ^ n;
            n = (m & n) << 1;
            m = temp;
        }
        return m;
    }
}
