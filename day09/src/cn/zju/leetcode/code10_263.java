package cn.zju.leetcode;

public class code10_263 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] num = {2, 3, 5};
        for (int i : num) {
            while (n % i == 0) {
                n /= i;
            }
        }
        return n == 1;
    }

}
