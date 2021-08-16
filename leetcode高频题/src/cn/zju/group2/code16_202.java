package cn.zju.group2;

public class code16_202 {
    // 快乐数一定会经过1 或 4，经过1的就是快乐数。
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
}
