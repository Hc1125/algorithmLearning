package cn.zju.leetcode;

public class code15_1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (1 - grumpy[i]) * customers[i];
        }
        int ret = 0;
        for (int i = 0; i < X; i++) {
            ret += grumpy[i] * customers[i];
        }
        int cur = ret;
        for (int i = X; i < n; i++) {
            cur += grumpy[i] * customers[i] - grumpy[i - X] * customers[i - X];
            ret = Math.max(ret, cur);
        }
        return ans + ret;
    }
}
