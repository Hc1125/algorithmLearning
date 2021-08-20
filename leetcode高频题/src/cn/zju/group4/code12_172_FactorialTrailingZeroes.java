package cn.zju.group4;

public class code12_172_FactorialTrailingZeroes {
    // 5的因子数一定比2的因字数少
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
