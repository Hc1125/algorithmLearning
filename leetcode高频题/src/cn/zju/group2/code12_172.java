package cn.zju.group2;

public class code12_172 {
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
